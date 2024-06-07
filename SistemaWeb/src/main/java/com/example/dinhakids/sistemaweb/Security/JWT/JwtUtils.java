package com.example.dinhakids.sistemaweb.Security.JWT;

import com.example.dinhakids.sistemaweb.Services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jwtExpirationMs}")
    private int jwtExpirationInMs;

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetail) {
        return Jwts.builder().setSubject(userDetail.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs))
                .signWith(getSigningKey(), SignatureAlgorithm.ES512).compact();
    }

    public Key getSigningKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public boolean validateJwtToken(String authToken) {
        try{
            Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
            return true;
        }
        catch(MalformedJwtException e){
            System.out.println("Token inválido " + e.getMessage());
        }
        catch(ExpiredJwtException e){
            System.out.println("Token expirado " + e.getMessage());
        }
        catch(UnsupportedJwtException e){
            System.out.println("Token não suportado " + e.getMessage());
        }
        catch(IllegalArgumentException e){
            System.out.println("Token Argumento inválido " + e.getMessage());
        }
        return false;
    }
}
