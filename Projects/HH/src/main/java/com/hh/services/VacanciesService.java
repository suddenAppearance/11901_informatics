package com.hh.services;

import com.hh.dto.UserDto;
import com.hh.dto.VacancyForm;
import com.hh.models.User;
import com.hh.models.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacanciesService {
    List<Vacancy> vacanciesOf(String login);
    List<Vacancy> vacancies();
    void createVacancy(VacancyForm vacancyForm, UserDto userDto);
    void delete(Long id);
    Optional<Vacancy> findVacancy(Long id);
    void update(Vacancy vacancy);
    List<Vacancy> allVacancies();//одно и то же vacancies() не знаю зачем добавил
    void like(Vacancy vacancy, User user);
    void unlike(Vacancy vacancy, User user);
    boolean is_liked(Vacancy vacancy, User user);
    List<Vacancy> saved(User user);

}
