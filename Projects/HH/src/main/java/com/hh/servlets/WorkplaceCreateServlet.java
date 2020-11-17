package com.hh.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hh.listener.SkeletonListener;
import com.hh.models.User;
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

@WebServlet("/workplace/create")
public class WorkplaceCreateServlet extends HttpServlet {
    ObjectMapper objectMapper;
    WorkplacesService workplacesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        workplacesService = (WorkplacesService) context.getAttribute("workplacesService");
        objectMapper = (ObjectMapper) context.getAttribute("objectMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Workplace workplace = objectMapper.readValue(req.getReader(), Workplace.class);
        resp.setContentType("text/html; " + SkeletonListener.ENCODING);
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        Long id = workplacesService.save(workplace);
        workplace.setId(id);
        try {
            Class.forName("org.javax.json.Json");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.getWriter().write(objectMapper.writeValueAsString(workplace));
    }
}
