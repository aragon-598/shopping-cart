package com.store.shopping_cart.shoppingcart.security.configuration;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.store.shopping_cart.shoppingcart.security.models.UserDetailsImp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {
    
    @Value("${jwt.secret}")
    private String secret;


    @Value("${jwt.expiration}")
    private int exp;
    
    /**
     * Generate jwt to authentication
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        List<String>  roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .claim("roles", roles)
                    .setExpiration(new Date(new Date().getTime()+exp*180))
                    .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                    .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * chekc if token if valid, expired
     * @param token
     * @return
     */
    public boolean validateToken(String token) {

        try {

            Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);

            return true;
            
        } catch (MalformedJwtException e) {
            log.error("Token mal formado", e);
        }
        catch (UnsupportedJwtException e) {
            log.error("Token no soportado", e);
        }
        catch (ExpiredJwtException e) {
            log.error("Token expirado", e);
        }
        catch (IllegalArgumentException e) {
            log.error("Token vacío", e);
        }

        return false;
    }

    /**
     * Refresh token
     * @param token
     * @return
     * @throws ParseException
     */
    public String refreshToken(String token) throws ParseException {
            try {
                
                Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);
            } catch (ExpiredJwtException e) {
                Claims claims = Jwts.parserBuilder()
                                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                                    .build()
                                    .parseClaimsJws(token)
                                    .getBody();

                String username = claims.getSubject();
                @SuppressWarnings("unchecked")
                List<String> roles = claims.get("roles",List.class);
           
            return Jwts.builder()
                        .setSubject(username).
                        claim("roles",roles).
                        setExpiration(new Date(new Date().getTime()+exp*180))
                        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                        .compact();
            }

            return null;
    }


}
