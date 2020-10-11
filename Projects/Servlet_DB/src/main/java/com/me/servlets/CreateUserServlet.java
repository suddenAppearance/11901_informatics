package com.me.servlets;

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

@WebServlet("/insert")
public class CreateUserServlet extends HttpServlet {
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "d06042001";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static Connection connection;
    private UsersRepository usersRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/html/insert.html").forward(req, resp);
    }

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
        String firstName = req.getParameter("name_input");
        String lastName = req.getParameter("surname_input");
        Integer age = Integer.valueOf(req.getParameter("age_input"));
        String login = req.getParameter("login_input");
        //no hash
        String pass_hash = req.getParameter("password_input");
        usersRepository.save(User.builder()
                .age(age)
                .lastName(lastName)
                .firstName(firstName)
                .login(login)
                .password_hash(pass_hash)
                .build());
    }
}
