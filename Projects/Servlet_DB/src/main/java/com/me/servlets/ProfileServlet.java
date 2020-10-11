package com.me.servlets;

import com.me.Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){
            resp.setStatus(302);
            resp.setHeader("Location", "/login");
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(req, resp);
    }
}
