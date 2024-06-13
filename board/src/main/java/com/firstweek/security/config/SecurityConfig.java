package com.firstweek.security.config;


import com.firstweek.security.filter.JwtAuthFilter;
import com.firstweek.security.filter.RefreshTokenFilter;
import com.firstweek.security.handler.UserLoginFailHandler;
import com.firstweek.security.jwt.JwtUtil;
import com.firstweek.security.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //필터체인에 필요한 구성요소 자동 생성
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true) // 메서드 수준 보안 검사 활성화
@AllArgsConstructor // 생성자 자동생성
public class SecurityConfig {

    private final UserService userService;
    private final JwtUtil jwtUtil;


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserLoginFailHandler userLoginFailHandler, RefreshTokenFilter refreshTokenFilter) throws Exception {

        http
                .authorizeRequests((requests)->requests
                        .requestMatchers("/login","/register","/").permitAll()
                        .requestMatchers("/post/**").authenticated()
                        .anyRequest().permitAll()
                )
                .logout(logout->{
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/");
                    logout.invalidateHttpSession(true); // HTTP 세션 무효화
                    logout.deleteCookies("JSESSIONID"); // 쿠키 삭제
                });



        http.csrf(csrf->csrf.disable());
        http.cors(Customizer.withDefaults());

        //세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 또는 사용 X
        http.sessionManagement(sessionManagemet -> sessionManagemet.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //FormLogin, BasicHttp  비활성화
        http.formLogin(formLogin -> formLogin.loginProcessingUrl("/login"));
        http.httpBasic((httpBasic)->httpBasic.disable());

        //JSTAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
        http.addFilterBefore(new JwtAuthFilter(userService,jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(refreshTokenFilter, JwtAuthFilter.class);

        http.exceptionHandling((exception)->{});



        return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}