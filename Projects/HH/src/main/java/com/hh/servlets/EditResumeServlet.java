package com.hh.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh.dto.UserDto;
import com.hh.dto.WorkplaceDto;
import com.hh.dto.WorkplaceForm;
import com.hh.listener.SkeletonListener;
import com.hh.models.Resume;
import com.hh.models.User;
import com.hh.models.Workplace;
import com.hh.services.ResumesServiceImpl;
import com.hh.services.WorkplacesServiceImpl;
import lombok.SneakyThrows;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet("/resume/edit")
public class EditResumeServlet extends HttpServlet {
    WorkplacesServiceImpl workplacesServiceImpl;
    ResumesServiceImpl resumesServiceImpl;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        resumesServiceImpl = (ResumesServiceImpl) servletContext.getAttribute("resumesService");
        workplacesServiceImpl = (WorkplacesServiceImpl) servletContext.getAttribute("workplacesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String param = req.getParameter("id");
        if ((param) != null) {
            Long id = Long.parseLong(param);
            Resume resume = resumesServiceImpl.findResume(id).orElse(null);
            if (resume == null) {
                resp.setStatus(404);
                return;
            }
            if (!resume.getAccount().getLogin().equals(((UserDto) req.getSession().getAttribute("user")).getLogin())) {
                resp.setStatus(403);
                return;
            }
            resp.setContentType("text/html; " + SkeletonListener.ENCODING);
            resp.setCharacterEncoding(SkeletonListener.ENCODING);
            VelocityContext velocityContext = new VelocityContext();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            velocityContext.put("df", dateFormat);
            velocityContext.put("workplaces", WorkplaceDto.listFrom(workplacesServiceImpl.workplacesOf(resume)));
            velocityContext.put("objectMapper", objectMapper);
            velocityContext.put("user", req.getSession().getAttribute("user"));
            velocityContext.put("resume", resume);
            Velocity.mergeTemplate("resume_edit.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
            return;
        }
        param = req.getParameter("resume_id");
        if ((param) != null) {
            Resume resume = Resume.builder()
                    .id(Long.parseLong(param))
                    .name(req.getParameter("name"))
                    .contact_info(req.getParameter("contact_info"))
                    .salary(Integer.parseInt(req.getParameter("salary")))
                    .schedule(req.getParameter("schedule"))
                    .description(req.getParameter("description"))
                    .type(req.getParameter("type"))
                    .account(User.from((UserDto) req.getSession().getAttribute("user")))
                    .sphere(req.getParameter("sphere"))
                    .moving(Boolean.parseBoolean(req.getParameter("moving")))
                    .readyToBusinessTrip(Boolean.parseBoolean(req.getParameter("readyToBusinessTrip")))
                    .build();
            resumesServiceImpl.update(resume);
            String[] r = req.getParameterValues("workplaces");
            if (r != null) {
                for (String workplaceJson : r
                ) {
                    WorkplaceForm workplaceForm = objectMapper.readValue(workplaceJson, WorkplaceForm.class);
                    Workplace workplace = Workplace.from(workplaceForm);
                    if(workplace.getId() != null){
                        workplacesServiceImpl.update(workplace);
                        continue;
                    }
                    workplace.setResume(resume);
                    workplacesServiceImpl.save(workplace);
                }
            }
            resp.sendRedirect("/profile/resumes");
        }
    }
}
