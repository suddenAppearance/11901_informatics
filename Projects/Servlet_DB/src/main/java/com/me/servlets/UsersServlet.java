package com.me.servlets;

import com.me.repositories.UsersRepository;
import com.me.repositories.UsersRepositoryJdbcImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "d06042001";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static UsersRepository usersRepository;
    private static Connection connection;

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

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("age") == null) req.setAttribute("users", usersRepository.findAll());
        else req.setAttribute("users", usersRepository.findAllByAge(Integer.parseInt(req.getParameter("age"))));
        req.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(req, resp);
    }
}
