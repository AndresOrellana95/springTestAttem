package com.demo.donations.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.donations.service.ValidateTokenService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class AuthFilter extends BasicAuthenticationFilter {

    private ValidateTokenService validateTokenService;

    public AuthFilter(AuthenticationManager authManager, ValidateTokenService validateTokenService) {
        super(authManager);
        this.validateTokenService = validateTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String token = request.getHeader("token");
            if(token != null) {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        } catch(Exception e) {
            throw new ServletException(e.getMessage());
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws Exception {
        if(token != null) {
            boolean validate = validateTokenService.validateToken(token);
            if(validate) {
                return new UsernamePasswordAuthenticationToken(token, null, new ArrayList());
            }
            return null;
        }
        throw new Exception("Authentication error");
    }
}
