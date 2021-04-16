package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.services.UsersService;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private UsersService usersService;
    @GetMapping("/users/search")
    public List<UserDto> search_users(String email){
        return usersService.findAlikeEmail(email);
    }
}
