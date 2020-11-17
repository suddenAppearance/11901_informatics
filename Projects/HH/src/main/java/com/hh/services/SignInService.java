package com.hh.services;

import com.hh.dto.UserDto;
import com.hh.dto.UserForm;

public interface SignInService {
    UserDto signIn(UserForm userForm);
}
