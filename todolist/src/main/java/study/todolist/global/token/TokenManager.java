package study.todolist.global.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.todolist.dto.TokenDto;
import study.todolist.global.error.ErrorCode;
import study.todolist.global.error.exception.AuthenticationException;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class TokenManager {

    private final String accessTokenExpirationTime;

    private final String refreshTokenExpirationTime;

    private final String tokenSecret;

    public TokenDto createJwtTokenDto(Long id, String username){
        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(id, username, accessTokenExpireTime);
        String refreshToken = createRefreshToken(id, refreshTokenExpireTime);

        return TokenDto.builder()
                .id(id)
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .build();
    }

    public Date createAccessTokenExpireTime(){

        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Date createRefreshTokenExpireTime(){

        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    public String createAccessToken(Long id, String username, Date expirationTime){
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name())                // token의 제목
                .setIssuedAt(new Date(System.currentTimeMillis()))  // token이 생성된 시간 (현재 시간)
                .setExpiration(expirationTime)                      // 만료 시간
                .claim("id", id)                              // 회원 id
                .claim("username", username)                  // 회원 username
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("type", "JWT")
                .compact();

        return accessToken;
    }

    public String createRefreshToken(Long id, Date expirationTime){
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())
                .setIssuedAt(new Date())
                .setExpiration(expirationTime)
                .claim("id", id)
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("type", "JWT")
                .compact();

        return refreshToken;
    }

    // 토큰 검증
    public String validateToken(String token){

        String username = null;

        try {
            Jwts.parser()
                    .setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody()
                    .get("username"); //token parsing
        } catch (ExpiredJwtException e){
            log.info("token 만료", e);
            throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e){
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }

        return username;
    }

    public Claims getTokenClaims(String token){
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token).getBody();
        } catch (Exception e){
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }

        return claims;
    }
}
