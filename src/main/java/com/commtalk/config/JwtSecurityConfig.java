package com.commtalk.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.commtalk.security.JwtTokenFilter;
import com.commtalk.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void configure(HttpSecurity http) throws Exception {
  	JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
  	http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }

}