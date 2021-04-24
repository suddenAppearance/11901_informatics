package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.services.UsersService;

@Controller
public class ProfileController {

    @Autowired
    UsersService usersService;

    @GetMapping("/profile")
    public String getProfile(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        User user = usersService.findByEmail(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("confirmed", user.getState().equals(State.CONFIRMED));
        return "profile_page";
    }
}
