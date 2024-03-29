package com.store.shopping_cart.shoppingcart.security.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.store.shopping_cart.shoppingcart.security.dto.LoginRequest;
import com.store.shopping_cart.shoppingcart.security.dto.LoginResponse;
import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;
import com.store.shopping_cart.shoppingcart.security.models.Role;

@Service
public interface AuthService {

    /**
     * authenticates user by credentails a return user details & token
     * @param loginRequest
     * @return
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     * ccreate a new user
     * @param user
     * @return
     */
    UserResponse register(UserRequest user);

    /**
     * map roles from string to RolName
     * @param roleList
     * @return
     */
    Set<Role> setRoles(Set<String> roleList);
}

