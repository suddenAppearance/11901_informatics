package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.User;
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

@WebServlet("/vacancy")
public class VacancyViewServlet extends HttpServlet {
    VacanciesServiceImpl vacanciesServiceImpl;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        vacanciesServiceImpl = (VacanciesServiceImpl) context.getAttribute("vacanciesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        Vacancy vacancy = vacanciesServiceImpl.findVacancy(id).orElse(null);
        if (vacancy == null) {
            resp.sendError(404);
            return;
        }
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("vacancy", vacancy);
        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM yyyy HH:mm");
        velocityContext.put("creation_date", df.format(vacancy.getCreationDate()));
        if (req.getSession().getAttribute("user") != null){
            velocityContext.put("is_liked", vacanciesServiceImpl.is_liked(vacancy, User.from((UserDto) req.getSession().getAttribute("user"))));
            velocityContext.put("user", req.getSession().getAttribute("user"));
        }
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        Velocity.mergeTemplate("vacancy.vm", SkeletonListener.ENCODING, velocityContext, resp.getWriter());
    }
}
