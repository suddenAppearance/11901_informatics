package com.hh.servlets;

import com.hh.dto.UserDto;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VelocityContext context = new VelocityContext();
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        resp.setContentType("text-html; UTF-8");
        resp.setCharacterEncoding("UTF-8");
        context.put("username", userDto.getLogin());
        Velocity.mergeTemplate("profile.vm", "UTF-8", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
