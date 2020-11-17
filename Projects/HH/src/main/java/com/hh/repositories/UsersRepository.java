package com.hh.repositories;

import com.hh.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByLogin(String login);
    void delete(String login);
    boolean changeLogin(String oldLogin, String login);
    void changePassword(String login,String password_hash);
}
