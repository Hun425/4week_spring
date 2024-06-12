package com.firstweek.security.service;

import com.firstweek.security.domain.CustomUser;
import com.firstweek.security.domain.User;
import com.firstweek.security.jwt.JwtUtil;
import com.firstweek.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new CustomUser(user);
//    }

    @Override
    @Transactional
    public String login(String username){
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("존재하지 않는 유저입니다");
        }

        CustomUser info = new CustomUser(userRepository.findByUsername(username));
        String accessToken = jwtUtil.generateToken(info);

        return accessToken;
    }
}
