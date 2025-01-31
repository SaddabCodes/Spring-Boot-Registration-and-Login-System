package com.sadcode.registrationloginsystem.controller;

import com.sadcode.registrationloginsystem.dto.UserDto;
import com.sadcode.registrationloginsystem.entity.User;
import com.sadcode.registrationloginsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    /* handler method to handler home page request */
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    /* handler method to handler user registration form request  */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        /* create model object to store form data*/
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    /* handler method to handler user registration form submit request  */
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result, Model model
    ) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }
        userService.savedUser(userDto);
        return "redirect:/register?success";

    }


}
