package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.Vacancy;
import com.hh.models.User;
import com.hh.services.VacanciesServiceImpl;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/profile/vacancies/saved")
public class SavedVacanciesServlet extends HttpServlet {
    VacanciesServiceImpl vacanciesServiceImpl;
    @Override
    public void init(ServletConfig config) throws ServletException {
        vacanciesServiceImpl = (VacanciesServiceImpl) config.getServletContext().getAttribute("vacanciesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        VelocityContext velocityContext = new VelocityContext();
        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM yyyy HH:mm");
        velocityContext.put("df", df);
        List<Vacancy> vacancyList = vacanciesServiceImpl.saved(User.from((UserDto) req.getSession().getAttribute("user")));
        velocityContext.put("vacancies", vacancyList);
        velocityContext.put("user", req.getSession().getAttribute("user"));
        Velocity.mergeTemplate("saved_vacancies.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }
}
