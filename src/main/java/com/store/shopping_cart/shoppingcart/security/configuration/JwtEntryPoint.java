package com.store.shopping_cart.shoppingcart.security.configuration;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtEntryPoint implements AuthenticationEntryPoint {@Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
                
        log.error("Error en metodo commence entry point", authException);

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED," No autorizado");
    }

}
