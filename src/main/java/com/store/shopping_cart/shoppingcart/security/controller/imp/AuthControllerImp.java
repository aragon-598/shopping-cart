package com.store.shopping_cart.shoppingcart.security.controller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.shopping_cart.shoppingcart.security.controller.AuthController;
import com.store.shopping_cart.shoppingcart.security.dto.LoginRequest;
import com.store.shopping_cart.shoppingcart.security.dto.LoginResponse;
import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;
import com.store.shopping_cart.shoppingcart.security.service.AuthService;

@RestController
public class AuthControllerImp implements AuthController {
    
        @Autowired
    AuthService authService;
    
    @Override
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(login));
    }
    
    @Override
    public ResponseEntity<?> register(@RequestBody UserRequest user) {
        UserResponse userResponse = authService.register(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

}
