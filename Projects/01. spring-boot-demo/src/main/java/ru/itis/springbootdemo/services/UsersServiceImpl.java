package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.dto.UsersPage;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.List;

import static ru.itis.springbootdemo.dto.UserDto.*;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }

    @Override
    public UserDto getUser(Long userId) {
        return from(usersRepository.findById(userId).orElse(User.builder().build()));
    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(User.builder().build());
    }


    @Override
    public boolean containsUsername(String username) {
        return usersRepository.findByUsername(username).isPresent();
    }

    @Override
    public UsersPage search(Integer size, Integer page, String q, String sortParameter, String directionParameter) {
        Direction direction = Direction.ASC;
        Sort sort = Sort.by(direction, "id");
        if (directionParameter != null) {
            direction = Direction.fromString(directionParameter);

        }
        if (sortParameter != null) {
            sort = Sort.by(direction, sortParameter);
        }
        if (q == null) {
            q = "empty";
        }
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<User> usersPage = usersRepository.search(q, pageRequest);
        return UsersPage.builder()
                .pagesCount(usersPage.getTotalPages())
                .users(from(usersPage.getContent()))
                .build();
    }


}
