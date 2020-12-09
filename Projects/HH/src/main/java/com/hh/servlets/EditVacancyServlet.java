package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.dto.VacancyForm;
import com.hh.listener.SkeletonListener;
import com.hh.models.Vacancy;
import com.hh.services.VacanciesServiceImpl;
import com.hh.services.ValidationService;
import com.hh.services.ValidationServiceImpl;
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
import java.util.Optional;

@WebServlet("/vacancy/edit")
public class EditVacancyServlet extends HttpServlet {
    VacanciesServiceImpl vacanciesServiceImpl;
    ValidationService validationService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        vacanciesServiceImpl = (VacanciesServiceImpl) servletContext.getAttribute("vacanciesService");
        validationService = (ValidationService) servletContext.getAttribute("validationService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        Vacancy vacancy = vacanciesServiceImpl.findVacancy(id).orElse(null);
        if (vacancy == null) {
            resp.setStatus(404);
            return;
        }
        if (!validationService.editing_or_deleting_is_permited(userDto, vacancy)){
            resp.setStatus(403);
            return;
        }
        resp.setContentType("text/html; " + SkeletonListener.ENCODING);
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("user", userDto);
        velocityContext.put("vacancy", vacancy);
        Velocity.mergeTemplate("vacancy_edit.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        VacancyForm vacancyForm = VacancyForm.builder()
                .id(Long.parseLong(req.getParameter("id")))
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
        vacanciesServiceImpl.update(Vacancy.from(vacancyForm));
        resp.sendRedirect("/profile/vacancies");
    }
}
