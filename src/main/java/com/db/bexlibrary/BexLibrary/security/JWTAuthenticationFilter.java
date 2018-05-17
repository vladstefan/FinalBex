package com.db.bexlibrary.BexLibrary.security;

import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.EXPIRATION;
import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.HEADER_STRING;
import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.SECRET;
import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.TOKEN_PREFIX;


import com.db.bexlibrary.BexLibrary.entities.AppUserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {



  AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    try {
      AppUserDTO user = new ObjectMapper().readValue(request.getInputStream(), AppUserDTO.class);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    ZonedDateTime expirationTimeUTC = ZonedDateTime.now(ZoneOffset.UTC)
        .plus(EXPIRATION, ChronoUnit.MILLIS);
    String token = Jwts.builder().setSubject(((User) authResult.getPrincipal()).getUsername())
        .setExpiration(Date.from(expirationTimeUTC.toInstant()))
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();
    response
        .addHeader("Set-Cookie", HEADER_STRING + "=" + TOKEN_PREFIX + token + "; Path=/; HttpOnly");
    response.addHeader("User", ((User) authResult.getPrincipal()).getAuthorities().toString());


  }


}
