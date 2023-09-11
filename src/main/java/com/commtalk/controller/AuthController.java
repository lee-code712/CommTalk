package com.commtalk.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commtalk.dto.AccountDTO;
import com.commtalk.security.JwtTokenProvider;

@RestController
@RequestMapping(value="/api/auth")
public class AuthController {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> command) {
//    	String encodedPassword = passwordEncoder.encode(command.get("password").toString());
//    	System.out.println(encodedPassword);
        // 사용자 인증을 시도합니다.
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(command.get("nickname"), command.get("password"))
        );

        // JWT 토큰을 생성합니다.
        String jwtToken = jwtTokenProvider.generateToken(authentication);
        
        JSONObject response = new JSONObject();
        response.put("token", jwtToken);

        // JWT 토큰을 응답으로 반환합니다.
        return ResponseEntity.ok(response.toJSONString());
    }
    
}
