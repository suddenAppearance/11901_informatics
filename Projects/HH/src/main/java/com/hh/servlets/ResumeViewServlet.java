package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.Resume;
import com.hh.models.User;
import com.hh.models.Workplace;
import com.hh.services.ResumesServiceImpl;
import com.hh.services.WorkplacesServiceImpl;
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
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/resume")
public class ResumeViewServlet extends HttpServlet {
    ResumesServiceImpl resumesServiceImpl;
    WorkplacesServiceImpl workplacesServiceImpl;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        resumesServiceImpl = (ResumesServiceImpl) context.getAttribute("resumesService");
        workplacesServiceImpl = (WorkplacesServiceImpl) context.getAttribute("workplacesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Resume resume = resumesServiceImpl.findResume(id).orElse(null);
        if (resume == null) {
            resp.sendError(404);
            return;
        }
        VelocityContext velocityContext = new VelocityContext();
        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM yyyy HH:mm");
        velocityContext.put("creation_date", df.format(resume.getCreated()));
        velocityContext.put("resume", resume);
        List<Workplace> workplaceList = workplacesServiceImpl.workplacesOf(resume);
        velocityContext.put("workplaces", workplaceList);
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        if (userDto != null ){
            velocityContext.put("user", userDto);
            velocityContext.put("is_liked", resumesServiceImpl.is_liked(resume, User.from(userDto)));
        }
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        Velocity.mergeTemplate("resume.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }
}
