package com.firstweek.security.controller;


import com.firstweek.security.domain.CustomUser;
import com.firstweek.security.domain.User;
import com.firstweek.security.jwt.TokenPair;
import com.firstweek.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/auth")
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<TokenPair> getMemberProfile(
            @Validated @RequestBody User user
            ){
        CustomUser customUser = new CustomUser(user);
        TokenPair token = this.authService.login(customUser.getUsername());

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
