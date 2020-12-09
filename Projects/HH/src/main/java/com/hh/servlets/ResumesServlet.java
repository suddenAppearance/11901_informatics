package com.hh.servlets;

import com.hh.listener.SkeletonListener;
import com.hh.models.Resume;
import com.hh.services.ResumesServiceImpl;
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

@WebServlet("/resumes")
public class ResumesServlet extends HttpServlet {
    ResumesServiceImpl resumesServiceImpl;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        resumesServiceImpl = (ResumesServiceImpl) servletContext.getAttribute("resumesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Resume> resumes = resumesServiceImpl.allResumes();
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("resumes", resumes);
        velocityContext.put("user", req.getSession().getAttribute("user"));
        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM yyyy HH:mm");
        velocityContext.put("df", df);
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        Velocity.mergeTemplate("resumes.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }
}
