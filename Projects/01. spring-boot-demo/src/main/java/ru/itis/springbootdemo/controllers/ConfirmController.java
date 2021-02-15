package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springbootdemo.services.ConfirmService;

/**
 * 15.02.2021
 * 08. spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Controller
public class ConfirmController {
    @Autowired
    ConfirmService confirmService;
    @GetMapping("/confirm/{code}")
    public String confirmUser(@PathVariable("code") String code) {
        // TODO: реализовать сервис для подтерждения (найти по коду человека и поставить ему статус CONFIRMED)
        // TODO: вернуть страницу об успешном прохождении подтверждения
        confirmService.confirmEmail(code);
        return "email_confirmation_succeed";
    }
}
