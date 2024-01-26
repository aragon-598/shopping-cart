package com.store.shopping_cart.shoppingcart.security.service;

import java.util.Set;

import com.store.shopping_cart.shoppingcart.security.dto.LoginRequest;
import com.store.shopping_cart.shoppingcart.security.dto.LoginResponse;
import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;
import com.store.shopping_cart.shoppingcart.security.models.Role;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    UserResponse register(UserRequest user);

    Set<Role> setRoles(Set<String> roleList);
}

