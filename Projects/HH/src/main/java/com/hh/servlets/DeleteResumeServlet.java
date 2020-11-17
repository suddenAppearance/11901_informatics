package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.models.Resume;
import com.hh.services.ResumesService;
import com.hh.services.VacanciesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resume/delete")
public class DeleteResumeServlet extends HttpServlet {
    ResumesService resumesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        resumesService = (ResumesService) servletContext.getAttribute("resumesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        resumesService.delete(id);
        resp.sendRedirect("/profile/resumes");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
