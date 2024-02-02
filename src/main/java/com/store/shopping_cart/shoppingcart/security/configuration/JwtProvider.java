package com.store.shopping_cart.shoppingcart.security.configuration;

import java.security.Key;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.store.shopping_cart.shoppingcart.security.models.UserDetailsImp;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
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
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+exp*5))
                    .signWith((getSecret(secret)), SignatureAlgorithm.HS256)
                    .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * chekc if token if valid, expired
     * @param token
     * @return
     */
    public boolean validateToken(String token) {

        try {

            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);

            return true;
            
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			log.error("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			log.error("EXPIRED TOKEN", ex);
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
                
                Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
            } catch (ExpiredJwtException e) {
                JWT jwt = JWTParser.parse(token);
                JWTClaimsSet claims = jwt.getJWTClaimsSet();
                String username = claims.getSubject();
                @SuppressWarnings("unchecked")
                List<String> roles =  (List<String>) claims.getClaim("roles");
           
            // return "token nuevo";                                     .setSigningKey()
            return Jwts.builder()
                        .setSubject(username)
                        .claim("roles",roles)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(new Date().getTime()+exp*5))
                        .signWith((getSecret(secret)), SignatureAlgorithm.HS256)
                        .compact();
            }

            return null;
    }

       private Key getSecret(String secret){
        
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


}
