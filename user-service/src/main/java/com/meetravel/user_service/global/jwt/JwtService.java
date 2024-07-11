package com.meetravel.user_service.global.jwt;

import com.meetravel.user_service.domain.auth.dto.response.LoginResponse;
import com.meetravel.user_service.domain.user.enums.TokenType;
import com.meetravel.user_service.global.security.CustomUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtService {

    private final Key key;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProperties jwtProperties;

    // application.yml에서 secret 값 가져와서 key에 저장
    public JwtService(CustomUserDetailsService customUserDetailsService, JwtProperties jwtProperties) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.customUserDetailsService = customUserDetailsService;
        this.jwtProperties = jwtProperties;

    }

    public LoginResponse createJwtToken(String userId, boolean isTemporary) {

        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + jwtProperties.getAccess().getExpiration());
        Date refreshTokenExpiresIn = new Date(now + jwtProperties.getRefresh().getExpiration());

        // Access Token 생성
        String accessToken = createAccessToken(userId, isTemporary, accessTokenExpiresIn);
        // Refresh Token 생성
        String refreshToken = createRefreshToken(userId, isTemporary, refreshTokenExpiresIn);

        return LoginResponse.builder()
                .grantType("Bearer ")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshTokenExpiresIn(refreshTokenExpiresIn.getTime())
                .build();
    }

    public Map<String, Object> setClaims(String userId, boolean isTemporary) {
        // 클레임 설정
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("isTemporary", isTemporary);
        return claims;
    }

    public String createAccessToken(String userId, boolean isTemporary, Date expirationTime) {

        // 클레임 설정
        Map<String, Object> claims = this.setClaims(userId, isTemporary);

        return Jwts.builder()
                .setSubject(TokenType.ACCESS.name())    // 토큰 제목
                .setIssuedAt(new Date())                // 토큰 발급 시간
                .setExpiration(expirationTime)          // 토큰 만료 시간
                .setClaims(claims)                  // 클레임 설정
                .signWith(key)
                .compact();

    }

    public String createRefreshToken(String userId, boolean isTemporary, Date expirationTime) {
        // 클레임 설정
        Map<String, Object> claims = this.setClaims(userId, isTemporary);
        return Jwts.builder()
                .setSubject(TokenType.REFRESH.name())   // 토큰 제목
                .setIssuedAt(new Date())                // 토큰 발급 시간
                .setExpiration(expirationTime)          // 토큰 만료 시간
                .setClaims(claims)      // 클레임 설정
                .signWith(key)
                .setHeaderParam("typ", "JWT")
                .compact();
    }

    // Jwt 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드


    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token, HttpServletRequest request) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | io.jsonwebtoken.security.SignatureException e) {
            log.info("Invalid JWT Token", e);
            return false;
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            return false;
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            return false;
        } catch (IllegalArgumentException | DecodingException e) {
            log.info("JWT claims string is empty.", e);
            return false;
        }
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String getAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(jwtProperties.getAccess().getHeader());

        if (authorizationHeader != null && !authorizationHeader.equals("")) {
            if (authorizationHeader.startsWith("Bearer") && authorizationHeader.length() > 7) {
                String accessToken = authorizationHeader.substring(7); // accesstoken 추출
                return accessToken;
            }
        }

        return null; // 헤더 비어있으면 null 리턴
    }


    public String getRefreshToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(jwtProperties.getRefresh().getHeader());

        if (authorizationHeader != null && !authorizationHeader.equals("")) {
            if (authorizationHeader.startsWith("Bearer") && authorizationHeader.length() > 7) {
                String refreshToken = authorizationHeader.substring(7); // refresh 추출
                return refreshToken;
            }
        }
        return null; // 헤더 비어있으면 null 리턴
    }

    // Authenticaiton 가져오기
    public Authentication getAuthentication(String accessToken, boolean isTemporary) {
        String userId = this.getUserId(accessToken);

        UserDetails userDetails = isTemporary
                ? customUserDetailsService.loadUserByTemporaryToken(userId) // 임시 토큰인 경우
                : customUserDetailsService.loadUserByUsername(userId); // 정식 토큰인 경우

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 userId 추출
    public String getUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userId", String.class);
    }


    // 토큰에서 임시토큰여부 추출
    public boolean getIsTemporary(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("isTemporary", Boolean.class);
    }

    public Long getExpiration(String accessToken) {
        // accessToken 남은 유효시간
        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().getExpiration();
        // 현재 시간
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

    // 응답 헤더에 accessToken 세팅
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("Authorization", "Bearer " + accessToken);
    }

    // 응답 헤더에 refreshToken 세팅
    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("Authorization-refresh", "Bearer " + refreshToken);
    }
}
