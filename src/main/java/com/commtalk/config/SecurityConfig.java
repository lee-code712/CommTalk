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
	    http
	        .csrf().disable();
	    http
	        .authorizeRequests()
	        .antMatchers("/api/**").permitAll() // 인증 불필요 URI
	        .antMatchers("/api/main/updatePinnedBoards",
					"/api/main/getMember",
					"/api/post/changeEngagementAction",
					"/api/post/createComment").authenticated()   // 인증 필요 URI
	        .anyRequest().permitAll();
	    http
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http
		    .logout()
		    .logoutUrl("/api/auth/logout");
	    http
			.apply(new JwtSecurityConfig(jwtTokenProvider));

	    return http.build();
	  }

}