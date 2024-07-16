package com.example.firstcrud.securite;

import com.example.firstcrud.Entity.Jwt;
import com.example.firstcrud.Entity.User;
import com.example.firstcrud.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service

public class JwtFilter extends  OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String email = null;
        Jwt jwtDansBdd = null;
        String token = null;
        Boolean tokenExpiration = true;
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            jwtDansBdd=jwtService.findByvaleur(token);
            tokenExpiration = jwtService.isExpired(token);
            email = jwtService.loadUserName(token);
        }
        if (!tokenExpiration
                && email != null
                && jwtDansBdd.getUser().getEmail().equals(email)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken autheticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autheticationToken);
        }
        filterChain.doFilter(request, response);
    }
 }
