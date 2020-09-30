package com.me.servlets;

import com.me.DAO.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@WebServlet("/insert")
public class CreateUserServlet extends HttpServlet {
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "d06042001";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static Connection connection;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/html/insert.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("name_input");
        String lastName = req.getParameter("surname_input");
        Integer age = Integer.valueOf(req.getParameter("age_input"));
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        UserDAO userDAO = new UserDAO(connection);
        userDAO.createUser(firstName, lastName, age);
        doGet(req, resp);
    }
}
