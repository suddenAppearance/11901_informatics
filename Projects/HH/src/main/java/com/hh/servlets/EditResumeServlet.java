package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.Resume;
import com.hh.services.ResumesService;
import com.hh.services.WorkplacesService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

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
    WorkplacesService workplacesService;
    ResumesService resumesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        resumesService = (ResumesService) servletContext.getAttribute("resumesService");
        workplacesService = (WorkplacesService) servletContext.getAttribute("workplacesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Resume resume = resumesService.findResume(id).orElse(null);
        if (resume == null){
            resp.setStatus(404);
            return;
        }
        if (!resume.getAccount().getLogin().equals(((UserDto) req.getSession().getAttribute("user")).getLogin())){
            resp.setStatus(403);
            return;
        }
        resp.setContentType("text/html; " + SkeletonListener.ENCODING);
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        VelocityContext velocityContext = new VelocityContext();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        velocityContext.put("df", dateFormat);
        velocityContext.put("workplaces", workplacesService.workplacesOf(resume));
        velocityContext.put("user", req.getSession().getAttribute("user"));
        velocityContext.put("resume", resume);
        Velocity.mergeTemplate("resume_edit.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }
}
