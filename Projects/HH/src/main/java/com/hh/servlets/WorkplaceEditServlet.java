package com.hh.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh.dto.WorkplaceForm;
import com.hh.listener.SkeletonListener;
import com.hh.models.Workplace;
import com.hh.repositories.WorkplacesRepository;
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

@WebServlet("/workplace/edit")
public class WorkplaceEditServlet extends HttpServlet {
    WorkplacesService workplacesService;
    ObjectMapper objectMapper;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        workplacesService = (WorkplacesService) context.getAttribute("workplacesService");
        objectMapper = (ObjectMapper) context.getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; " + SkeletonListener.ENCODING);
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        WorkplaceForm workplaceForm = objectMapper.readValue(req.getReader(), WorkplaceForm.class);
        workplacesService.update(Workplace.from(workplaceForm));
        resp.getWriter().write(objectMapper.writeValueAsString(workplaceForm));
    }
}
