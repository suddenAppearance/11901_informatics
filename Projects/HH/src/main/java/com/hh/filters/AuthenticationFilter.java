package com.hh.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // берем сессию у запроса
        // берем только существующую, если сессии не было - то вернет null
        HttpSession session = request.getSession(false);
        // флаг, аутентифицирован ли пользователь
        boolean isAuthenticated = false;
        // существует ли сессия вообще?
        boolean sessionExists = session != null;
        // идет ли запрос на страницу входа или регистрации?
        String requestUri = request.getRequestURI();
        boolean isRequestOnOpenPage = requestUri.equals("/login") ||
                requestUri.equals("/signUp") || requestUri.equals("/vacancies") || requestUri.equals("/resumes")
                || requestUri.equals("/vacancy") || requestUri.equals("/resume");
        // если сессия есть
        boolean isRequestOnPageWithNoNeedToAuthorize = requestUri.equals("/vacancies") || requestUri.equals("/resumes")
                || requestUri.equals("/vacancy") || requestUri.equals("/resume") || requestUri.equals("/resume/search") || requestUri.equals("/vacancies/search");
        if (sessionExists) {
            // проверим, есть ли атрибует user?
            isAuthenticated = session.getAttribute("user") != null;
        }

        // если авторизован и запрашивает не открытую страницу или если не авторизован и запрашивает открытую
        if (isAuthenticated && !isRequestOnOpenPage || !isAuthenticated && isRequestOnOpenPage || isRequestOnPageWithNoNeedToAuthorize) {
            // отдаем ему то, что он хочет
            filterChain.doFilter(request, response);
        } else if (isAuthenticated) {
            // пользователь аутенцифицирован и запрашивает страницу входа
            // - отдаем ему профиль
            response.sendRedirect("/profile");
        } else {
            // если пользователь не аутенцицицирован и запрашивает другие страницы
            response.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }
}
