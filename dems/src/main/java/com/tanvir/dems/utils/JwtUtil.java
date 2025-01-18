package com.tanvir.dems.utils;

import io.jsonwebtoken.Claims;

import java.util.function.Function;

public class JwtUtil {

    private String secret = "letsTestThisAsASecretKey"; //This should not be that simple. I will come up something later.
    private long jwtExpiration = 3600000; //one hour


    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
}
