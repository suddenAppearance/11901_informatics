package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.models.User;

import java.util.List;

public interface UsersService {
    List<UserDto> all();

    UserDto findById(Long id);

    User findByToken(String token);

    UserDto save(User user);

    UserDto update(Long id, UserForm userForm);

    void delete(Long id);
}
