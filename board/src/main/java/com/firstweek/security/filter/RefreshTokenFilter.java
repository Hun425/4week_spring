package com.firstweek.security.filter;

import com.firstweek.security.domain.CustomUser;
import com.firstweek.security.jwt.JwtUtil;
import com.firstweek.security.jwt.TokenPair;
import com.firstweek.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class RefreshTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String refreshToken = request.getHeader("refreshToken");

        // Refresh Token이 없거나 유효하지 않은 경우 요청을 체인으로 전달
        if (refreshToken == null || !jwtUtil.validateToken(refreshToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Refresh Token이 유효하면 새로운 Acces Token을 생성
        String username = jwtUtil.getUsernameFromToken(refreshToken);
        CustomUser customUser = userService.loadUserByUsername(username);
        TokenPair newTokens = jwtUtil.generateToken(customUser);

        //응답 헤더에 새로운 Access Token과 Refresh Token 설정
        response.setHeader("Access-Token", newTokens.getAccessToken());
        response.setHeader("Refresh-Token", newTokens.getRefreshToken());

        //요청을 필터체인으로 전달
        filterChain.doFilter(request,response);

    }

}
