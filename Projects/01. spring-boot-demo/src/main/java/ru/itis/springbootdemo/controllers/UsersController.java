package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.dto.UserForm;
import ru.itis.springbootdemo.services.SignUpService;
import ru.itis.springbootdemo.services.UsersService;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("usersList", usersService.getAllUsers());
        return "users_page";
    }

    @GetMapping("/users/{user-id}")
    @ResponseBody
    public ResponseEntity<UserDto> getUser(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(usersService.getUser(userId));
    }
}
