package com.demo.donations.service;

import java.util.Date;
import java.util.function.Function;

import com.demo.donations.model.objects.UserDetails;

import io.jsonwebtoken.Claims;

public interface ValidateTokenService {

    public String extractUserName(String token);

    public Date extractExpiration(String token);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    public String generateToken(UserDetails userDetails);

    public Boolean validateToken(String token);

    public Claims extractAllClaims(String token);
}
