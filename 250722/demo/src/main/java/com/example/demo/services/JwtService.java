package com.example.demo.services;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {


    private long EXPIRATION_TIME; //預期過期時間(毫秒)

    public String generateToken(User user){
        return buildToken(user); ////需要一個 build token 的方法
    }

    public String buildToken(User user){
        return Jwts.builder()
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+600000)) //未來時間 (現在時間+過期的時段) //毫秒!!! (10分鐘)
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact(); //將剛剛的東西都壓在一起! 變成一串 String
    }

    private Key getKey(){
        byte[] keyByte = Decoders.BASE64.decode("dGlueXNhbWVoYW5kc29tZXlvdW5nY2FsbHJlY29yZGdpZnRpbnZlbnRlZHdpdGhvdXQ=");
        return Keys.hmacShaKeyFor(keyByte); //
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

}
