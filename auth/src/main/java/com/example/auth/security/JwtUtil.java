package com.example.auth.security;

import com.example.auth.constant.JwtConstant;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class JwtUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public static String generateToken(String subject, int validDuration, List<String> role, int id) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .claim("id", id)
                .claim("role", role != null && role.size() > 0 ? role.get(0) : "")
//                .claim("firstName", firstName)
//                .claim("lastName", lastName)
                .setIssuedAt(now)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(validDuration).toInstant()))
                .signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SIGNING_KEY);

        return builder.compact();
    }

    public static String getSubjectFromJwt(String token) {
        try {
            String subject = Jwts.parser().setSigningKey(JwtConstant.JWT_SIGNING_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return subject;
        } catch (SignatureException e) {
            LOGGER.warn("Invalid Jwt Signature");
            return null;
        } catch (ExpiredJwtException e) {
            LOGGER.warn("Expired Jwt");
            return "expired";
        } catch (Exception e) {
            LOGGER.warn("Exception Parsing Jwt");
            return null;
        }
    }
    public static Claims getClaimsFromJwt(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(JwtConstant.JWT_SIGNING_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (SignatureException e){
            LOGGER.warn("Invalid Jwt Signature");
            return null;
        } catch (ExpiredJwtException e){
            LOGGER.warn("Expired Jwt");
            return null;
        } catch (Exception e){
            LOGGER.warn("Exception Parsing Jwt");
            return null;
        }
    }
}
