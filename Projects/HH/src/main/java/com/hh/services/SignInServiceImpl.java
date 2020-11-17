package com.hh.services;

import com.hh.dto.UserDto;
import com.hh.dto.UserForm;
import com.hh.models.User;
import com.hh.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

public class SignInServiceImpl implements SignInService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signIn(UserForm userForm) {
        User user = usersRepository.findByLogin(userForm.getLogin()).orElse(null);
        if (user == null) throw new UsernameNotFoundException("Неправильное имя пользователя или пароль");
        if (passwordEncoder.matches(userForm.getPassword(), user.getPassword_hash())) return UserDto.from(user);
        else throw new UsernameNotFoundException("Неправильное имя пользователя или пароль");
    }
}
