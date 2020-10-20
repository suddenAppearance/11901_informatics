package com.me.repositories;

import com.me.Models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
    User authorize(String login, String password);
    List<User> findAllByNameStartingWith(String name);
}

