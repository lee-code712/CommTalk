package com.commtalk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Configuration
    public static class ApiSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
        
    	@Override
        public void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/api/**")
                .authorizeRequests(authorizeRequests ->
	                authorizeRequests
		                .antMatchers("/api/auth/**").permitAll() // 특정 URL에 대한 접근 권한 설정
		                .anyRequest().authenticated() // 나머지 요청은 인증된 사용자만 허용
                )
                .httpBasic()
                	.and()
                .formLogin() // 폼 기반 로그인 설정
                	.loginPage("/login") // 로그인 페이지 지정
                	.permitAll()
                	.and()
                .logout()
                	.permitAll();
        }
    	
    }
    
}
