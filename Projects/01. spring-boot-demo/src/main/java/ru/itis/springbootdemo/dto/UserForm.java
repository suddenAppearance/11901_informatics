package ru.itis.springbootdemo.dto;

import lombok.Data;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
public class UserForm {
    private String email;
    private String username;
    private String phone;
    private String password;
}
