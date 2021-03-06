package com.hh.servlets;

import com.hh.dto.UserForm;
import com.hh.listener.SkeletonListener;
import com.hh.services.SignUpService;
import com.hh.services.ValidationServiceImpl;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private SignUpService signUpService;
    private ValidationServiceImpl validationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        validationService = (ValidationServiceImpl) servletContext.getAttribute("validationService");
        signUpService = (SignUpService) servletContext.getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;" + SkeletonListener.ENCODING);
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        String username = req.getParameter("username");
        if (username != null) {
            if (validationService.username_is_not_taken(username)) {
                resp.getWriter().write("Valid");
            } else
                resp.getWriter().write("Invalid");
        } else {
            VelocityContext context = new VelocityContext();
            Writer writer = resp.getWriter();
            Velocity.getTemplate("signup.vm").merge(context, writer);
            writer.flush();
            writer.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        signUpService.signUp(
                UserForm.builder().
                        login(req.getParameter("username"))
                        .password(req.getParameter("password"))
                        .build());
        resp.sendRedirect("/login");
    }
}
