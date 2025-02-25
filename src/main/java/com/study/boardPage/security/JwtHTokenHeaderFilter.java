package com.study.boardPage.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtHTokenHeaderFilter {
//    private final SecretKey secretKey;
//
//    public JwtHTokenHeaderFilter(SecretKey secretKey) {
//        @Value("${jwt.secret}") String key
//    } {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
//    }
    @Value("${jwt.secret}") // application.properties에 설정된 키
    private String secretKey;

    public String getUserIdFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        Claims claims = Jwts.parser()  // ✅ JJWT 0.12.x 방식
                .verifyWith((SecretKey) key) // 서명 검증
                .build()
                .parseSignedClaims(token)
                .getPayload(); // ✅ 페이로드 추출

        return claims.getSubject(); // userId를 subject에 저장했다고 가정
    }
}
