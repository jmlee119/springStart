package com.study.boardPage.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
// JWT를 생성하고 검증
public class JwtTokenProvider {
    private final Key key;
    private final long accessTokenValiditySeconds;
    private final long refreshTokenValiditySeconds;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String key,
            @Value("${jwt.token-validity-in-seconds}") long accessTokenValiditySeconds,
            @Value("${jwt.token-validity-in-seconds}") long refreshTokenValiditySeconds
        ) {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }
    // access token 발급
    public String getAccessToken(int userId) {
        return createToken(String.valueOf(userId), accessTokenValiditySeconds);
    }

    //refresh token 발급
    public String getRefreshToken(int userId) {
        return createToken(String.valueOf(userId), refreshTokenValiditySeconds);
    }


    // token 발급
    public String createToken(String userId, long exp) {
        return Jwts.builder()
                // subject : 토큰의 주인(user id)를 나타냄
                .subject(userId)
                //토큰일 발급된 시간
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + exp))
                // jwt signature 생성
                //HS256 알고리즘을 사용하여 서명
                .signWith(key, SignatureAlgorithm.HS256)
                // jwt 토큰을 문자열 형태로 반환
                .compact();
    }
}
