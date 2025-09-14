package com.hm_map.hm_map.util;

import com.hm_map.hm_map.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "mysecretkey";
    private static final long EXPIRATION_TIME = 86400000;

    public static String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getAccount())
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)

                .compact();
    }
}