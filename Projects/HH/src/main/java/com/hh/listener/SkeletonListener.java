package com.hh.listener;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.hh.repositories.*;
import com.hh.services.*;
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

        ValidationServiceImpl validationService = new ValidationServiceImpl(usersRepository);
        VacanciesServiceImpl vacanciesServiceImpl = new VacanciesServiceImpl(vacanciesRepository);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder, validationService);
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        WorkplacesServiceImpl workplacesServiceImpl = new WorkplacesServiceImpl(workplacesRepository);
        ResumesServiceImpl resumesServiceImpl = new ResumesServiceImpl(resumesRepository);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        StdDateFormat dateFormat = new StdDateFormat().withColonInTimeZone(true);
        objectMapper.setDateFormat(dateFormat);
        servletContext.setAttribute("objectMapper", objectMapper);

        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("validationService", validationService);
        servletContext.setAttribute("vacanciesService", vacanciesServiceImpl);
        servletContext.setAttribute("workplacesService", workplacesServiceImpl);
        servletContext.setAttribute("resumesService", resumesServiceImpl);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
