package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.dto.UserForm;
import com.hh.listener.SkeletonListener;
import com.hh.services.SignInService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/login")
public class SignInServlet extends HttpServlet {

    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        signInService = (SignInService) context.getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VelocityContext context = new VelocityContext();
        resp.setContentType("text/html;  " + SkeletonListener.ENCODING);
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        Writer writer = resp.getWriter();
        context.put("auth_error", req.getAttribute("auth_error"));
        Velocity.mergeTemplate("login.vm", SkeletonListener.ENCODING, context, writer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDto userDto = signInService.signIn(UserForm.builder()
                    .login(req.getParameter("username"))
                    .password(req.getParameter("password"))
                    .build());
            HttpSession session = req.getSession();
            session.setAttribute("user", userDto);
            resp.sendRedirect("/profile");
        } catch (UsernameNotFoundException e) {
            req.setAttribute("auth_error", e.getMessage());
            doGet(req, resp);
        }

    }
}
