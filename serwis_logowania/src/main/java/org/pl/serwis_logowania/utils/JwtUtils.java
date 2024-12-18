package org.pl.serwis_logowania.utils;

import io.jsonwebtoken.*;
import org.pl.serwis_logowania.entities.Role;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.*;


@Component
public class JwtUtils {

    private static final String jwtSecret="ThisIsASecureSecretKeyForHS512ThatIsAtLeast64CharactersLongAndMore";

    private static final String encodedKey = Base64.getEncoder().encodeToString(jwtSecret.getBytes());

    private static final long jwtExpirationMs=900000;

    public static String generateJwtToken(String login, List<Role> roles) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles.stream().map(Enum::name).toList());
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .subject(login)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .issuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, encodedKey)
                .compact();
    }

    public static boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(encodedKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("JWT Token is not valid: " + e.getMessage());
        }
        return false;
    }

    public static Claims getClaimsFromJwtToken(String token) {
        if (validateJwtToken(token)) {
            return Jwts.parser()
                    .setSigningKey(encodedKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        return null;
    }

    public static List<Role> getRolesFromToken(String token) {
        Claims claims = getClaimsFromJwtToken(token);
        List<String> rolesAsString = (List<String>) claims.get("roles");
        return rolesAsString.stream()
                .map(Role::valueOf) // Konwersja String to Enum
                .toList();
    }

    public static String getUsernameFromJwtToken(String token) {
        Claims claims = getClaimsFromJwtToken(token);
        return claims.getSubject();
    }
}