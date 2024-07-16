package com.firstweek.security.service;

import com.firstweek.security.domain.User;
import com.firstweek.security.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.Collections;
import java.util.Map;

public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();

        User user = processOAuth2User(attributes);

        return new DefaultOAuth2User(Collections.singleton(new OAuth2UserAuthority(attributes)),
                attributes, userNameAttributeName);
    }

    private User processOAuth2User(Map<String, Object> attributes) {
        String id = attributes.get("id").toString(); // 여기서 "id"는 카카오에서 제공하는 사용자 ID의 속성 이름입니다.
        return userRepository.findByUsername(id)
                .orElseGet(() -> userRepository.save(new User(id))); // User 엔티티의 생성자에 사용자 이름(id)를 넣어서 저장합니다.
    }
}
