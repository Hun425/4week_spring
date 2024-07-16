package com.firstweek.security.controller;

import com.firstweek.security.domain.User;
import com.firstweek.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public ResponseEntity<String> GetRegisterForm() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> PostRegisterForm(User user) {
        userService.save(user);

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> GetLoginForm(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
