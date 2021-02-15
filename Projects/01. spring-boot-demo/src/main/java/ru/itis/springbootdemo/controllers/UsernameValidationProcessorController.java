package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springbootdemo.services.UsersService;

@Controller
public class UsernameValidationProcessorController {
    @Autowired
    UsersService usersService;

    @ResponseBody
    @GetMapping("/usernameValidationProcessor")
    public String validateUsername(String username){
        if (usersService.containsUsername(username)){
            return "Invalid";
        }
        else return "Valid";
    }
}
