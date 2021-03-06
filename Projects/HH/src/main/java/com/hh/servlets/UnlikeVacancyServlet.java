package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.User;
import com.hh.models.Vacancy;
import com.hh.services.VacanciesServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vacancy/unlike")
public class UnlikeVacancyServlet extends HttpServlet {
    VacanciesServiceImpl vacanciesServiceImpl;
    @Override
    public void init(ServletConfig config) throws ServletException {
        vacanciesServiceImpl = (VacanciesServiceImpl) config.getServletContext().getAttribute("vacanciesService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        vacanciesServiceImpl.unlike(Vacancy.builder().id(Long.parseLong(req.getParameter("id"))).build(), User.from((UserDto) req.getSession().getAttribute("user")));
        resp.setContentType("text/plain; utf-8");
        resp.setCharacterEncoding(SkeletonListener.ENCODING);

        resp.getWriter().println("done");
    }
}
