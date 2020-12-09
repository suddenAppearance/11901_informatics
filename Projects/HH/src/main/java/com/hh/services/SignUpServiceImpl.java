package com.hh.services;

import com.hh.dto.UserForm;
import com.hh.models.User;
import com.hh.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignUpServiceImpl implements SignUpService {
    public SignUpServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, ValidationService validationService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;
    ValidationService validationService;
    @Override
    public void signUp(UserForm userForm) {
        if (!validationService.username_is_valid(userForm.getLogin())){
           new Exception("Username invalid").printStackTrace();
           return;
        }
        User user = User.builder().login(userForm.getLogin())
                .password_hash(passwordEncoder.encode(userForm.getPassword())).build();
        usersRepository.save(user);
    }
}
