package ru.itis.springbootdemo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.springbootdemo.models.User;

import java.util.List;
import java.util.Optional;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByConfirmCode(String confirmCode);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Query("select user from User user where (:q = 'empty' or upper(user.email) like upper(concat('%', :q, '%')))")
    Page<User> search(@Param("q")  String q, Pageable pageable);
}
