package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.models.Role;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.UsersService;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;


    @Override
    public List<UserDto> all() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public UserDto findById(Long id) {
        return UserDto.from(usersRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user")));
    }

    @Override
    public User findByToken(String token) {
        return usersRepository.findByToken(token).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid token"));
    }

    @Override
    public UserDto save(User user) {
        return UserDto.from(usersRepository.save(user));
    }

    @Override
    public UserDto update(Long id, UserForm userForm) {
        User user = usersRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user"));
        user.setFullName(userForm.getFullName());
        user.setToken(userForm.getToken());
        user.setRole(Role.valueOf(userForm.getRole()));
        return UserDto.from(usersRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user");
    }

}
