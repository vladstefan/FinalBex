package com.db.bexlibrary.BexLibrary.security;

import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.PREFIX_SIZE;
import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.SECRET;
import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.TOKEN_PREFIX;

import com.db.bexlibrary.BexLibrary.service.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


  Logger logger = LoggerFactory.getLogger(this.getClass());

  private CustomUserDetailsService customUserDetailsService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
      CustomUserDetailsService customUserDetailsService) {
    super(authenticationManager);
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String header = request.getHeader("Cookie");
    if (header == null) {
      chain.doFilter(request, response);
      logger.info("User request has failed authoriziation filter");
      return;
    }
    UsernamePasswordAuthenticationToken username = getAuthentificationToken(request);
    if (username == null) {
      chain.doFilter(request, response);
      logger.info("User request has failed authoriziation filter");
      return;
    }
    SecurityContextHolder.getContext().setAuthentication(username);
    logger.info("User request has passed authoriziation filter");
    chain.doFilter(request, response);
  }


  private UsernamePasswordAuthenticationToken getAuthentificationToken(HttpServletRequest request) {
    String token = request.getHeader("Cookie");
    token = token.substring(PREFIX_SIZE);
    if (!token.startsWith(TOKEN_PREFIX)) {
      return null;
    }
    String username = null;
    try {
      username = Jwts.parser().setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody().getSubject();
    } catch (Exception e) {
      logger.error("Incorrect token");
      return null;
    }

    UserDetails user = customUserDetailsService.loadUserByUsername(username);
    if (username != null) {
      return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    } else {
      return null;
    }
  }
}
