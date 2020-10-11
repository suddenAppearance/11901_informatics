package com.me.servlets;

import com.me.Models.User;
import com.me.repositories.UsersRepository;
import com.me.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "d06042001";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static Connection connection;
    private UsersRepository usersRepository;

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
        String login = req.getParameter("username");
        String password = req.getParameter("password");
        User user = usersRepository.authorize(login, password);

        if (user == null) {
            resp.setStatus(302);
            resp.setHeader("Location", "/login");
        } else {
            req.getSession().setAttribute("user", user);
            resp.setStatus(302);
            resp.setHeader("Location", "/profile");
        }
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.setStatus(302);
            resp.setHeader("Location", "/profile");
        }
        req.getRequestDispatcher("WEB-INF/html/login.html").forward(req, resp);
    }
}
