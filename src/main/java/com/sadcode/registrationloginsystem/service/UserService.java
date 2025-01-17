package com.sadcode.registrationloginsystem.service;

import com.sadcode.registrationloginsystem.dto.UserDto;
import com.sadcode.registrationloginsystem.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public interface UserService {
    void savedUser(UserDto userDto);

    User findUserByEmail(String email);
}
