package com.hh.servlets;

import com.hh.dto.UserDto;
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

@WebServlet("/profile/resumes")
public class MyResumesServlet extends HttpServlet {
    ResumesServiceImpl resumesServiceImpl;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        resumesServiceImpl = (ResumesServiceImpl) servletContext.getAttribute("resumesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        resp.setContentType("text/html; " + SkeletonListener.ENCODING);
        VelocityContext velocityContext = new VelocityContext();
        UserDto userDto = ((UserDto)req.getSession().getAttribute("user"));
        List<Resume> resumeList = resumesServiceImpl.resumesOf(userDto.getLogin());
        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM yyyy HH:mm");
        velocityContext.put("df", df);
        velocityContext.put("resumes", resumeList);
        velocityContext.put("user", userDto);
        Velocity.mergeTemplate("my_resumes.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
