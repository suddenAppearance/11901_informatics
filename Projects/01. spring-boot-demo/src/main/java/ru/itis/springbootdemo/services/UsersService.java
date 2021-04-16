package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.User;

import java.util.List;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersService {
    List<UserDto> getAllUsers();

    UserDto getUser(Long userId);

    User findByEmail(String email);

    boolean containsUsername(String username);

    List<UserDto> findAlikeEmail(String email);
}
