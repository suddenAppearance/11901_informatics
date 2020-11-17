package com.hh.listener;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.hh.repositories.*;
import com.hh.services.*;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.velocity.app.Velocity;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SkeletonListener implements ServletContextListener {
    public static final String ENCODING = "UTF-8";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/HH");
        dataSource.setUsername("postgres");
        dataSource.setPassword("d06042001");

        Velocity.setProperty("resource.loader", "file");
        Velocity.setProperty("input.encoding", ENCODING);
        Velocity.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.setProperty("directive.set.null.allowed", true);
        Velocity.init();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        VacanciesRepository vacanciesRepository = new VacanciesRepositoryJdbcTemplateImpl(dataSource, usersRepository);
        ResumesRepository resumesRepository = new ResumesRepositoryJdbcTemplateImpl(dataSource, usersRepository);
        WorkplacesRepository workplacesRepository = new WorkplacesRepositoryJdbcTemplateImpl(dataSource, resumesRepository);

        VacanciesService vacanciesService = new VacanciesService(vacanciesRepository);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder);
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        ValidationService validationService = new ValidationService(usersRepository);
        WorkplacesService workplacesService = new WorkplacesService(workplacesRepository);
        ResumesService resumesService = new ResumesService(resumesRepository);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        StdDateFormat dateFormat = new StdDateFormat().withColonInTimeZone(true);
        objectMapper.setDateFormat(dateFormat);
        servletContext.setAttribute("objectMapper", objectMapper);

        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("validationService", validationService);
        servletContext.setAttribute("vacanciesService", vacanciesService);
        servletContext.setAttribute("workplacesService", workplacesService);
        servletContext.setAttribute("resumesService", resumesService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
