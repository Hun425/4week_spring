package com.firstweek.security.dto;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class KakaoUserInfo {
    private final WebClient webClient;
    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    public KakaoUserInfoResponse getUserInfo(String token) {
        String uri = USER_INFO_URI ;

        Mono<KakaoUserInfoResponse> response = webClient.get()
                .uri(uri)
                .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8")
                .retrieve()
                .bodyToMono(KakaoUserInfoResponse.class);

        return response.block();
    }
}
