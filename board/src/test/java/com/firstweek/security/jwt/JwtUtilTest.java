package com.firstweek.security.jwt;

import com.firstweek.security.domain.CustomUser;
import com.firstweek.security.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private User user;
    private CustomUser customUser;

    @BeforeEach
    void setUp() {


    }


    @Test
    void generateToken() {
        //given
        user = new User();
        user.setId(1);
        user.setUsername("firstweek");
        user.setPassword("password");
        customUser = new CustomUser(user);


        //when

        JwtUtil jwtUtil = new JwtUtil("secretKey",100,300);
        CustomUser user = new CustomUser(new User());
        TokenPair token = jwtUtil.generateToken(customUser);

        //then
        assertNotNull(token.getAccessToken());
        assertNotNull(token.getRefreshToken());
        assertTrue(token.getAccessToken().length()>0);
        assertTrue(token.getRefreshToken().length()>0);
    }

    @Test
    void getUserIdFromToken() {

        //given
        user = new User();
        user.setId(1);
        user.setUsername("firstweek");
        user.setPassword("password");
        customUser = new CustomUser(user);


        //when
        JwtUtil jwtUtil = new JwtUtil("secretKey",100,300);
        TokenPair token = jwtUtil.generateToken(customUser);
        Long userId = jwtUtil.getUserIdFromToken(token.getAccessToken());

        //then
        assertNotNull(userId);
        assertEquals(customUser.getUserId(),userId);
    }

    @Test
    void getUsernameFromToken() {

        //given
        user = new User();
        user.setId(1);
        user.setUsername("firstweek");
        user.setPassword("password");
        customUser = new CustomUser(user);


        //when
        JwtUtil jwtUtil = new JwtUtil("secretKey",100,300);
        TokenPair token = jwtUtil.generateToken(customUser);
        String username = jwtUtil.getUsernameFromToken(token.getAccessToken());

        //then
        assertNotNull(username);
        assertEquals(customUser.getUsername(),username);
    }

    @Test
    void validateToken() {

        //given
        user = new User();
        user.setId(1);
        user.setUsername("firstweek");
        user.setPassword("password");
        customUser = new CustomUser(user);


        //when
        JwtUtil jwtUtil = new JwtUtil("secretKey",100,300);
        TokenPair token = jwtUtil.generateToken(customUser);
        boolean isVaild = jwtUtil.validateToken(token.getAccessToken());

        //then
        assertTrue(isVaild);

    }

    @Test
    void parseClaims() {

        //given
        user = new User();
        user.setId(1);
        user.setUsername("firstweek");
        user.setPassword("password");
        customUser = new CustomUser(user);


        //when

        JwtUtil jwtUtil = new JwtUtil("secretKey",100,300);
        TokenPair token = jwtUtil.generateToken(customUser);
        Long userId = jwtUtil.parseClaims(token.getAccessToken()).get("userId",Long.class);

        //then
        assertNotNull(userId);
        assertEquals(customUser.getUserId(),userId);

    }
}