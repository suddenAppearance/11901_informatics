package com.hh.servlets;

import com.hh.listener.SkeletonListener;
import com.hh.models.Workplace;
import com.hh.services.WorkplacesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/workplace/delete")

public class WorkplaceDeleteServlet extends HttpServlet {
    WorkplacesService workplacesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        workplacesService = (WorkplacesService) context.getAttribute("workplacesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        workplacesService.delete(id);
    }
}
