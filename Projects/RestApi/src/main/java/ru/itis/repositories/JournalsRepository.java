package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Journal;

public interface JournalsRepository extends JpaRepository<Journal, Long> {

}
