package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.models.Resume;
import com.hh.models.Vacancy;
import com.hh.services.VacanciesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vacancy/delete")
public class DeleteVacancyServlet extends HttpServlet {
    VacanciesService vacanciesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        vacanciesService = (VacanciesService) servletContext.getAttribute("vacanciesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        Vacancy vacancy = vacanciesService.findVacancy(id).orElse(null);
        if (vacancy == null){
            resp.setStatus(404);
            return;
        }
        if (!vacancy.getAccount().getLogin().equals(((UserDto) req.getSession().getAttribute("user")).getLogin())){
            resp.setStatus(403);
            return;
        }
        vacanciesService.delete(id);
        resp.sendRedirect("/profile/vacancies");
    }
}
