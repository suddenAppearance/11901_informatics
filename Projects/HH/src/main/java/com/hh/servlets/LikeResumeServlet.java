package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.models.Resume;
import com.hh.models.User;
import com.hh.models.Vacancy;
import com.hh.services.ResumesService;
import com.hh.services.VacanciesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/resume/like")
public class LikeResumeServlet extends HttpServlet {
    ResumesService vacanciesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        vacanciesService = (ResumesService) config.getServletContext().getAttribute("resumesService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vacanciesService.like(Resume.builder().id(Long.parseLong(req.getParameter("id"))).build(), User.from((UserDto) req.getSession().getAttribute("user")));
        resp.setStatus(200);
        resp.getWriter().write("done");
    }
}
