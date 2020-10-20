package com.me.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.Models.User;
import com.me.repositories.UsersRepository;
import com.me.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/search")
public class AjaxSearchServlet extends HttpServlet {
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "d06042001";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;
    private UsersRepository usersRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = objectMapper.readValue(req.getReader(), User.class);
        String users = objectMapper.writeValueAsString(usersRepository.findAllByNameStartingWith(user.getFirstName()));
        resp.setContentType("application/json");
        resp.getWriter().println(users);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/html/search.html").forward(req, resp);
    }
}
