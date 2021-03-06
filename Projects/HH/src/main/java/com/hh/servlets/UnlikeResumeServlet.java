package com.hh.servlets;

import com.hh.dto.UserDto;
import com.hh.listener.SkeletonListener;
import com.hh.models.User;
import com.hh.models.Resume;
import com.hh.services.ResumesServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resume/unlike")
public class UnlikeResumeServlet extends HttpServlet {
    ResumesServiceImpl resumesServiceImpl;
    @Override
    public void init(ServletConfig config) throws ServletException {
        resumesServiceImpl = (ResumesServiceImpl) config.getServletContext().getAttribute("resumesService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resumesServiceImpl.unlike(Resume.builder().id(Long.parseLong(req.getParameter("id"))).build(), User.from((UserDto) req.getSession().getAttribute("user")));
        resp.setContentType("text/plain; utf-8");
        resp.setCharacterEncoding(SkeletonListener.ENCODING);
        resp.getWriter().println("done");
    }
}
