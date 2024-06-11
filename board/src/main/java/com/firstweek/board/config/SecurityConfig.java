package com.firstweek.board.config;


import com.firstweek.board.handler.UserLoginFailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserLoginFailHandler userLoginFailHandler) throws Exception {
        http
                .authorizeRequests((requests)->requests
                        .requestMatchers("/login","/register","/").permitAll()
                        .requestMatchers("/post/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form->{
                    form.loginProcessingUrl("/login");
                    form.defaultSuccessUrl("/");
                    form.failureHandler(userLoginFailHandler);

                }).logout(logout->{
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/");
                    logout.invalidateHttpSession(true); // HTTP 세션 무효화
                    logout.deleteCookies("JSESSIONID"); // 쿠키 삭제
                });
        http.csrf(csrf->csrf.disable());

        return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}