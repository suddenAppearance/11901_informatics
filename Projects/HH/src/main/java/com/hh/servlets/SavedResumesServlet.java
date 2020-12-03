package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.Resume;
import com.hh.models.User;
import com.hh.services.ResumesService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/profile/resumes/saved")
public class SavedResumesServlet extends HttpServlet {
    ResumesService resumesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        resumesService = (ResumesService) config.getServletContext().getAttribute("resumesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        VelocityContext velocityContext = new VelocityContext();
        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM yyyy HH:mm");
        velocityContext.put("df", df);
        List<Resume> resumeList = resumesService.saved(User.from((UserDto) req.getSession().getAttribute("user")));
        velocityContext.put("resumes", resumeList);
        velocityContext.put("user", req.getSession().getAttribute("user"));
        Velocity.mergeTemplate("saved_resumes.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }
}
