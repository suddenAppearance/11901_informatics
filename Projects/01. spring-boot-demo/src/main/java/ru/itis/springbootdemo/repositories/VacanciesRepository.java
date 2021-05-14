package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootdemo.models.Vacancy;

import java.util.List;

public interface VacanciesRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByAccountEmail(String email);
}
