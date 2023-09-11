package com.commtalk.security;

import java.security.SecureRandom;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
  @Value("${jwt.secret}")
  private String secret_key;

  @Value("${jwt.expiration}")
  private long expire_time;

  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * 적절한 설정을 통해 토큰을 생성하여 반환
   * @param authentication
   * @return
   */
  public String generateToken(Authentication authentication) {

	CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    Claims claims = Jwts.claims().setSubject(authentication.getName());
    claims.put("memberId", userDetails.getMemberId());
    
    Date now = new Date();
    Date expiresIn = new Date(now.getTime() + expire_time);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiresIn)
        .signWith(SignatureAlgorithm.HS256, secret_key)
        .compact();
  }

  /**
   * 토큰으로부터 클레임을 만들고, 이를 통해 User 객체를 생성하여 Authentication 객체를 반환
   * @param token
   * @return
   */
  public Authentication getAuthentication(String token) {
    String username = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().getSubject();
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  /**
   * http 헤더로부터 bearer 토큰을 가져옴.
   * @param req
   * @return
   */
  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  /**
   * 토큰을 검증
   * @param token
   * @return
   */
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      // MalformedJwtException | ExpiredJwtException | IllegalArgumentException
//      throw new CustomException("Error on Token", HttpStatus.INTERNAL_SERVER_ERROR);
    	return false;
    }
  }
  
  public static void main(String[] args) {
	  String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	  
	  SecureRandom random = new SecureRandom();
      StringBuilder sb = new StringBuilder(64);

      for (int i = 0; i < 64; i++) {
          int randomIndex = random.nextInt(CHARACTERS.length());
          char randomChar = CHARACTERS.charAt(randomIndex);
          sb.append(randomChar);
      }
      
      System.out.println(sb.toString());
  }
  
  public Long getMemberId(HttpServletRequest req) {
	  String token = resolveToken(req);
	  Claims claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
	  return ((Integer)claims.get("memberId")).longValue();
  }
  
}
