package com.example.tkht_backend.jwt;

import com.example.tkht_backend.security.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${demo.jwt.secret}")
    private String JWT_secret;
    @Value("${demo.jwt.AcessTokenExpiration}")
    private int JWT_expiration;
    @Value("${demo.jwt.RefreshTokenExpiration}")
    private int JWT_refreshExpiration;

    public String refreshToken(UserDetail userDetail , Integer tokenId){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + JWT_refreshExpiration);
        Map<String ,Object> claim = new HashMap<>();
        claim.put("tokenId" , tokenId);
        return Jwts.builder().setSubject(userDetail.getUsername()).setIssuedAt(now).setClaims(claim).setExpiration(expiration).signWith(SignatureAlgorithm.HS512,JWT_secret).compact();
    }
    public String generateToken(UserDetail userDetail){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + JWT_expiration);
        return Jwts.builder().setSubject(userDetail.getUsername()).setIssuedAt(now).setExpiration(expiration).signWith(SignatureAlgorithm.HS512,JWT_secret).compact();
    }
    public String getUserNameFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(JWT_secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(JWT_secret).parseClaimsJws(authToken);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
