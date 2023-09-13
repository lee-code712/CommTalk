package com.commtalk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.commtalk.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    // Disable csrf to use token
	    http
	        .csrf().disable();

	    //
	    http
	        .authorizeRequests()
	        .antMatchers("/api/auth/**", 
	        		"/api/common/**",
	        		"/api/post/getPostsByBoard/**",
	        		"/api/post/getPostDetail/**").permitAll() // 인증 불필요 URI
	        .antMatchers("/api/**").authenticated()   // 인증 필요 URI
	        .anyRequest().permitAll();

	    // No session will be created or used by spring security
	    http
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http
		    .logout()
		    .logoutUrl("/api/auth/logout");
	    // Apply JWT
	    http.apply(new JwtSecurityConfig(jwtTokenProvider));

	    return http.build();
	  }

}