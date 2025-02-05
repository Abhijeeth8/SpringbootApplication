package com.healthcare.healthcare.Utility;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtility {

    private final String SECRET_KEY = "This_is_just_a_random_secret_key_used_for_jwt";

    public SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 36000000 ) )
                .signWith(getSigningKey())
                .compact();

    }

    public JwtParser getParser(){

        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build();
    }

    public Claims getClaims(String jwtToken){
        return getParser().parseSignedClaims(jwtToken).getPayload();
    }

    public boolean validateTokenExpiry(String jwtToken){
        Date tokenExpiration = getClaims(jwtToken).getExpiration();
        return tokenExpiration.after(new Date());
    }

    public Header getHeader(String jwtToken) {
        return getParser().parseSignedClaims(jwtToken).getHeader();
    }
}
