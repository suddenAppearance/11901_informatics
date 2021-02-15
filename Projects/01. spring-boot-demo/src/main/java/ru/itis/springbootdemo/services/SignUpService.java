package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.UserForm;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface SignUpService {
    void signUp(UserForm form);
}
