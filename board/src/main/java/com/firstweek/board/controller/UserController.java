package com.firstweek.board.controller;

import com.firstweek.board.entity.User;
import com.firstweek.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String GetRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String PostRegisterForm(User user) {
        userService.save(user);

        return "boardwrite";
    }

    @GetMapping("/login")
    public String GetLoginForm(){
        return "login";
    }
}
