package com.hh.repositories;

import com.hh.models.User;
import com.hh.models.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacanciesRepository extends CrudRepository<Vacancy> {
    List<Vacancy> vacanciesOf(String login);
    Optional<Vacancy> findById(Long id);
    void like(Vacancy vacancy, User user);
    void unlike(Vacancy vacancy, User user);
    boolean is_liked(Vacancy vacancy, User user);
    List<Vacancy> liked(User user);
}
