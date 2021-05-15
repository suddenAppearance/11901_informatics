package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Classroom;

public interface ClassroomsRepository extends JpaRepository<Classroom, Long> {
}
