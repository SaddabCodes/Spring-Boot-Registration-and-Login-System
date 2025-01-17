package com.sadcode.registrationloginsystem.service.impl;

import com.sadcode.registrationloginsystem.dto.UserDto;
import com.sadcode.registrationloginsystem.entity.Role;
import com.sadcode.registrationloginsystem.entity.User;
import com.sadcode.registrationloginsystem.repository.RoleRepository;
import com.sadcode.registrationloginsystem.repository.UserRepository;
import com.sadcode.registrationloginsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void savedUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());

        // encrypt the password using spring security
        user.setPassword(userDto.getPassword());

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
