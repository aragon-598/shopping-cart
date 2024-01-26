package com.store.shopping_cart.shoppingcart.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
public class LoginRequest {
    String username;
    String password;
}