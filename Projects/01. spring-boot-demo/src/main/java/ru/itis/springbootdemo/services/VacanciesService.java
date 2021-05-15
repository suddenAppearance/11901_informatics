package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.dto.VacancyForm;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.models.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacanciesService {
    List<Vacancy> vacanciesOf(String email);
    List<Vacancy> vacancies();
    void createVacancy(VacancyForm vacancyForm, String userEmail);
    void delete(Long id);
    Optional<Vacancy> findVacancy(Long id);
    void update(Vacancy vacancy);
    void like(Long vacancyId, String userEmail);
    void unlike(Long vacancyId, String userEmail);
    boolean isLiked(Long vacancyId, String userEmail);
}
