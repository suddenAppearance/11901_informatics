package com.me.servlets;

import com.me.DAO.UserDAO;
import com.me.Models.User;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "d06042001";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        UserDAO userDAO = new UserDAO(connection);
        req.setAttribute("users", userDAO.allUsers());
        req.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(req,resp);
    }
}
