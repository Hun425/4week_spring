package com.firstweek.security.filter;

import com.firstweek.security.domain.CustomUser;
import com.firstweek.security.jwt.JwtUtil;
import com.firstweek.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String refreshToken = request.getHeader("refreshToken");
        //JWT가 헤더에 있으면
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            //jwt 유효성 검증
            if(jwtUtil.validateToken(token)) {
                String username = jwtUtil.getUsernameFromToken(token);

                //유저와 토큰이 일치하면 userDetails 생성
                UserDetails userDetails = userService.loadUserByUsername(username);

                if(userDetails != null) {
                    //userDetails, Password, Role -> 접근권한 인증 Token 생성
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    //현재 Request와 Security context에 접근권한 설정
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        }

        filterChain.doFilter(request, response); // 다음 필터로 넘기기
    }
}
