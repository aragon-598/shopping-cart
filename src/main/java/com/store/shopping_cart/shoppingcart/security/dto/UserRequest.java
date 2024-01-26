package com.store.shopping_cart.shoppingcart.security.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
public class UserRequest {
    private String name;
    private String email;
    private String username;
    private String password;
    private boolean active;
    private Set<String> roles = new HashSet<>();
}
