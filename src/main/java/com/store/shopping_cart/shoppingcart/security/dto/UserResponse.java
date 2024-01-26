package com.store.shopping_cart.shoppingcart.security.dto;

import java.util.HashSet;
import java.util.Set;

import com.store.shopping_cart.shoppingcart.security.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    private String username;
    private boolean active;
    private Set<Role> roles = new HashSet<>();
}
