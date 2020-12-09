package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.models.Resume;
import com.hh.services.ResumesServiceImpl;

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
    ResumesServiceImpl resumesServiceImpl;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        resumesServiceImpl = (ResumesServiceImpl) servletContext.getAttribute("resumesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Resume resume = resumesServiceImpl.findResume(id).orElse(null);
        if (resume == null){
            resp.setStatus(404);
            return;
        }
        if (!resume.getAccount().getLogin().equals(((UserDto) req.getSession().getAttribute("user")).getLogin())){
            resp.setStatus(403);
            return;
        }
        resumesServiceImpl.delete(id);
        resp.sendRedirect("/profile/resumes");
    }
}
