package com.hh.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh.models.Workplace;
import com.hh.services.WorkplacesServiceImpl;

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
    WorkplacesServiceImpl workplacesServiceImpl;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        workplacesServiceImpl = (WorkplacesServiceImpl) context.getAttribute("workplacesService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Workplace workplace = new ObjectMapper().readValue(req.getReader(), Workplace.class);

        workplacesServiceImpl.delete(workplace.getId());
    }
}
