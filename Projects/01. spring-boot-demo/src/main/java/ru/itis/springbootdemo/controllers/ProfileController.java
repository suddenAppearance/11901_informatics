package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.services.UsersService;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    UsersService usersService;

    @GetMapping("/profile")
    public String getProfile(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        boolean conf = usersService.findByEmail(authentication.getName())
                .getState().equals(State.CONFIRMED);
        model.addAttribute("confirmed", conf);
        return "profile_page";
    }
}
