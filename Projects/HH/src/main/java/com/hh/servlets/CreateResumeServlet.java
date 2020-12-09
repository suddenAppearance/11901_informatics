package com.hh.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh.dto.UserDto;
import com.hh.dto.WorkplaceForm;
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

@WebServlet("/resume/create")
public class CreateResumeServlet extends HttpServlet {
    ResumesServiceImpl resumesServiceImpl;
    WorkplacesServiceImpl workplacesServiceImpl;
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        resumesServiceImpl = (ResumesServiceImpl) servletContext.getAttribute("resumesService");
        workplacesServiceImpl = (WorkplacesServiceImpl) servletContext.getAttribute("workplacesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; UTF-8");
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("user", req.getSession().getAttribute("user"));
        Velocity.mergeTemplate("resume_create.vm", "UTF-8", velocityContext, resp.getWriter());
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Resume resume = Resume.builder()
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
        Long identifier = resumesServiceImpl.save(resume);
        resume.setId(identifier);
        String[] r = req.getParameterValues("workplaces");
        if (r != null) {
            for (String workplaceJson : r
            ) {
                WorkplaceForm workplaceForm = objectMapper.readValue(workplaceJson, WorkplaceForm.class);
                Workplace workplace = Workplace.from(workplaceForm);
                workplace.setResume(resume);
                workplacesServiceImpl.save(workplace);
            }
        }

        resp.sendRedirect("/profile/resumes");

    }

}
