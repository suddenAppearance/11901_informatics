package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootdemo.models.Resume;

import java.util.List;

public interface ResumesRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByAccountEmail(String accountEmail);
}
