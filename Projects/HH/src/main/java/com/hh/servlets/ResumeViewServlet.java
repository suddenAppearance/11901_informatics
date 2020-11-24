package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.Resume;
import com.hh.models.User;
import com.hh.services.ResumesService;
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

@WebServlet("/resume")
public class ResumeViewServlet extends HttpServlet {
    ResumesService resumesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        resumesService = (ResumesService) context.getAttribute("resumesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Resume resume = resumesService.findResume(id).orElse(null);
        if (resume == null) {
            resp.sendError(404);
            return;
        }
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("resume", resume);
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        if (userDto != null ){
            velocityContext.put("user", userDto);
            velocityContext.put("is_liked", resumesService.is_liked(resume, User.from(userDto)));
        }
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        Velocity.mergeTemplate("resume.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }
}
