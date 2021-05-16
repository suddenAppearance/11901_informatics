package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.Journal;

import java.util.Optional;

public interface JournalsRepository extends JpaRepository<Journal, Long> {
    @Query(value = "select j from Journal j where j.classroom.id = :id and j.returned_at is null")
    Optional<Journal> getLastByClassroomId(@Param("id") Long id);
}
