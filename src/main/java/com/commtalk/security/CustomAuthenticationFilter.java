package com.commtalk.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 로그인 시도 시 호출되는 메서드
        // 사용자 정보를 가져와서 Custom Principal을 생성하고 SecurityContext에 저장
        // 예를 들어, 토큰 기반의 인증을 구현할 수 있음
        // ...
    	return null;
    }
}