package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.dto.VacancyForm;
import com.hh.listener.SkeletonListener;
import com.hh.services.VacanciesService;
import lombok.SneakyThrows;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

@WebServlet("/vacancy/create")
public class CreateVacancyServlet extends HttpServlet {
    VacanciesService vacanciesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        vacanciesService = (VacanciesService) servletContext.getAttribute("vacanciesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; " + SkeletonListener.ENCODING);
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        VelocityContext vc = new VelocityContext();
        vc.put("user", req.getSession().getAttribute("user"));
        Velocity.mergeTemplate("vacancy_create.vm", SkeletonListener.ENCODING, vc
                , resp.getWriter());
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        VacancyForm vacancyForm = VacancyForm.builder()
                .name(req.getParameter("name"))
                .address(req.getParameter("address"))
                .schedule(req.getParameter("schedule"))
                .contact_info(req.getParameter("contact_info"))
                .description(req.getParameter("description"))
                .experience(Integer.parseInt(Optional.ofNullable(req.getParameter("experience")).orElse("0")))
                .paymentSchedule(req.getParameter("payment_schedule"))
                .place(req.getParameter("place"))
                .requirements(req.getParameter("requirements"))
                .salary(Integer.parseInt(Optional.ofNullable(req.getParameter("salary")).orElse("0")))
                .sphere(req.getParameter("sphere"))
                .type(req.getParameter("type"))
                .build();
        vacanciesService.createVacancy(vacancyForm, (UserDto) req.getSession().getAttribute("user"));
        resp.sendRedirect("/profile/vacancies");
    }
}
