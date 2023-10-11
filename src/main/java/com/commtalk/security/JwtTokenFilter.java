package com.commtalk.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

//Request 이전에 1회 작동할 필터
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;
	private final String[] authList;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, String[] authList) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.authList = authList;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {

		if (PatternMatchUtils.simpleMatch(authList, request.getRequestURI())) {
			String token = jwtTokenProvider.resolveToken(request);
			try {
				// 토큰 인증
				if (token != null && jwtTokenProvider.validateToken(token)) {
					Authentication auth = jwtTokenProvider.getAuthentication(token);
					SecurityContextHolder.getContext().setAuthentication(auth);
				} else {
					response.sendError(403, "토큰 인증 실패");
					return;
				}
			} catch (Exception e) {
				response.sendError(403, e.getMessage());
				return;
			}
		}

		filterChain.doFilter(request, response); // 다음 필터 체인 실행
	}

}