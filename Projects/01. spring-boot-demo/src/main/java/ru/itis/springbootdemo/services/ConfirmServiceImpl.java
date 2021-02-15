package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.Optional;

@Component
public class ConfirmServiceImpl implements ConfirmService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void confirmEmail(String code) {
        Optional<User> optionalUser = usersRepository.findByConfirmCode(code);
        optionalUser.ifPresent(user -> {
            user.setState(State.CONFIRMED);
            usersRepository.save(user);
        });
        if (!optionalUser.isPresent()) {
            throw new IllegalStateException("Invalid operation");
        }
    }
}
