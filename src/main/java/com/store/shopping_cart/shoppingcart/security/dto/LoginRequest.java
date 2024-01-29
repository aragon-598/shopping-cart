package com.store.shopping_cart.shoppingcart.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
public class LoginRequest {

    @Schema(name = "username",required = true,example = "username",defaultValue = "username",description = "This key indicates the username")
    private String username;

    @Schema(name = "password",required = true,example = "password",defaultValue = "password",description = "This key indicates the password")
    private String password;

}