package com.db.bexlibrary.BexLibrary.security;

import com.db.bexlibrary.BexLibrary.service.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    private CustomUserDetailsService customUserDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  CustomUserDetailsService customUserDetailsService) {
        super(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken username = getAuthentificationToken(request);
       SecurityContextHolder.getContext().setAuthentication(username);
        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentificationToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        String username = null;
        try {
             username = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject();
        }catch (Exception e){
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
