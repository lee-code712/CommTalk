package com.commtalk.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Long memberId; // DB 조회를 위한 memberId 추가
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, Long memberId, List<String> roles) {
        this.username = username;
        this.password = password;
        this.memberId = memberId;
        this.setAuthorities(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    
    public Long getMemberId() {
        return memberId;
    }

    // 사용자 계정의 만료 여부를 반환 (기본적으로 true)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 사용자 계정의 잠김 여부를 반환 (기본적으로 true)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 사용자 계정의 패스워드 만료 여부를 반환 (기본적으로 true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사용자 계정의 활성 여부를 반환 (기본적으로 true)
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    // 역할(role) 정보 추가 메서드
    public void setAuthorities(Collection<String> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        this.authorities = authorities;
    }
}
