package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.Vacancy;
import com.hh.services.VacanciesService;
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

@WebServlet("/vacancies")
public class VacanciesServlet extends HttpServlet {
    VacanciesService vacanciesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        vacanciesService = (VacanciesService) servletContext.getAttribute("vacanciesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Vacancy> vacancies = vacanciesService.allVacancies();
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("vacancies", vacancies);
        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM yyyy HH:mm");
        velocityContext.put("df", df);
        velocityContext.put("user", (UserDto) req.getSession().getAttribute("user"));
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        Velocity.mergeTemplate("vacancies.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }
}
