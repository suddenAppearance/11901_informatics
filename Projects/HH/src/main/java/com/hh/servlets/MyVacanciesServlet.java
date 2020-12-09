package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.Vacancy;
import com.hh.services.VacanciesServiceImpl;
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

@WebServlet("/profile/vacancies")
public class MyVacanciesServlet extends HttpServlet {
    VacanciesServiceImpl vacanciesServiceImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        vacanciesServiceImpl = (VacanciesServiceImpl) servletContext.getAttribute("vacanciesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;" + SkeletonListener.ENCODING);
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        UserDto userDto = ((UserDto) req.getSession().getAttribute("user"));
        List<Vacancy> vacancyList = vacanciesServiceImpl.vacanciesOf(userDto.getLogin());
        VelocityContext velocityContext = new VelocityContext();
        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM yyyy HH:mm");
        velocityContext.put("df", df);
        velocityContext.put("vacancies", vacancyList);
        velocityContext.put("user", userDto);
        Velocity.mergeTemplate("my_vacancies.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
