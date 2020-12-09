package com.hh.services;

import com.hh.dto.UserDto;
import com.hh.models.Vacancy;
import com.hh.repositories.UsersRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {
    UsersRepository usersRepository;

    public ValidationServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean username_is_valid(String username) {
        Pattern pattern = Pattern.compile("([A-Za-z0-9].)*");
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) return false;
        if (username.contains("..")) return false;
        return !(username.charAt(0) == '.' | username.charAt(username.length() - 1) == '.');
    }

    public boolean username_is_not_taken(String username) {
        return !usersRepository.findByLogin(username).isPresent();
    }

    public boolean editing_or_deleting_is_permited(UserDto userDto, Vacancy vacancy){
        return vacancy.getAccount().getLogin().equals(userDto.getLogin());
    }
}
