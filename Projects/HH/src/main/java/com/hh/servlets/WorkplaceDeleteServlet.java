package com.hh.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.hh.listener.SkeletonListener;
import com.hh.models.Workplace;
import com.hh.services.WorkplacesService;

import javax.json.Json;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Workplace workplace = new ObjectMapper().readValue(req.getReader(), Workplace.class);

        workplacesService.delete(workplace.getId());
    }
}
