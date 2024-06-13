package com.firstweek.security.jwt;


import com.firstweek.security.domain.CustomUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final long accessTokenExpTime;
    private final long refreshTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey, //lombok @Value 아님 주의 !
            @Value("${jwt.expiration_time}") long accessTokenExpTime,
            @Value("${jwt.refresh_token_expiration_time}") long refreshTokenExpTime
    ){
        byte[] keyBytes = secretKey.getBytes();
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 보안 키 생성
        this.accessTokenExpTime = accessTokenExpTime;
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

    /**
     * Access Token 생성
     * @param customUser
     * @return Access Token String
     */
    public TokenPair generateToken(CustomUser customUser) {
        String accessToken = createToken(customUser,accessTokenExpTime);
        String refreshToken = createToken(customUser,refreshTokenExpTime);

        return new TokenPair(accessToken,refreshToken);
    }

    /**
     * JWT 생성
     * @param customUser
     * @param accessTokenExpTime
     * @return JWT String
     */
    private String createToken(CustomUser customUser, long accessTokenExpTime) {
        Claims claims = Jwts.claims();
        claims.put("userId", customUser.getUserId());
        claims.put("username", customUser.getUsername());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(accessTokenExpTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Token에서 userId 추출
     * @param token
     * @return UserId
     */
    public Long getUserIdFromToken(String token) {
        return parseClaims(token).get("userId",Long.class);
    }


    public String getUsernameFromToken(String token) {
        return parseClaims(token).get("username",String.class);
    }

    /**
     * JWT 검증
     * @param token
     * @return Isvalidtate
     */
    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        }catch(io.jsonwebtoken.security.SignatureException | MalformedJwtException e){
            log.info("Invalid JWT token",e);
        }catch(ExpiredJwtException e){
            log.info("Expired JWT token",e);
        }catch(UnsupportedJwtException e){
            log.info("Unsupported JWT token",e);
        }catch(IllegalArgumentException e){
            log.info("JWT claims string is empty",e);
        }
        return false;
    }

    /**
     * JWT Claims 추출
     * @param token
     * @return JWT Claims
     */
    public Claims parseClaims(String token) {
        try{
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        }catch(ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
