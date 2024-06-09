package com.firstweek.board.entity;

import java.util.Collection;
import java.util.Collections;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String password;
    private String username;

    // UserDetails 메서드들을 구현합니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 목록을 반환하도록 구현합니다.
        // 예를 들어, 여기서는 빈 권한 목록을 반환합니다.
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았음을 반환합니다.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠기지 않았음을 반환합니다.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명이 만료되지 않았음을 반환합니다.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화되어 있음을 반환합니다.
        return true;
    }
}
