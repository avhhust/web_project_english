package com.springpetproject.controllers;

import com.springpetproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



}
