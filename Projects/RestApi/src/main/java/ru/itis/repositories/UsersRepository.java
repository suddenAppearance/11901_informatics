package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
