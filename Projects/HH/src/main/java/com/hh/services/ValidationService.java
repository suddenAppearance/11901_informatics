package com.hh.services;

import com.hh.dto.UserDto;
import com.hh.models.Vacancy;

public interface ValidationService {
    boolean username_is_valid(String username);
    boolean editing_or_deleting_is_permited(UserDto userDto, Vacancy vacancy);
}
