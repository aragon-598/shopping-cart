package com.store.shopping_cart.shoppingcart.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@Configuration
@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.1",
        contact = @Contact(name = "Alejandro", email = "aragondru155@gmail.com")),
        security = {@SecurityRequirement(name = "basicAuth"), @SecurityRequirement(name = "bearerToken")}
)
@SecuritySchemes({
        @SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
})
public class OpenApiConfig {
    
}
