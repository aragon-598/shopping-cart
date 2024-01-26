package com.store.shopping_cart.shoppingcart.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;

@Service
@Transactional
public interface UserService {
    
    UserResponse getUserById(int userId);
    UserResponse getUserByUsername(String username);
    UserResponse saveUser(UserRequest user);
    boolean deleteUserById(int userId);
}
