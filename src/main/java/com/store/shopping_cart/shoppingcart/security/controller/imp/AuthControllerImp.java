package com.store.shopping_cart.shoppingcart.security.controller.imp;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.store.shopping_cart.shoppingcart.security.controller.AuthController;
import com.store.shopping_cart.shoppingcart.security.dto.AuthResponse;
import com.store.shopping_cart.shoppingcart.security.dto.LoginRequest;
import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;
import com.store.shopping_cart.shoppingcart.security.service.AuthService;

import jakarta.validation.Valid;


@RestController
public class AuthControllerImp implements AuthController {
    
        @Autowired
    AuthService authService;
    
    @Override
    public ResponseEntity<?> login(@Valid LoginRequest login, BindingResult result) {
        if (result.hasErrors()) 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los campos");
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(login));
    }
    
    @Override
    public ResponseEntity<?> register(@Valid UserRequest user, BindingResult result) {
        if (result.hasErrors()) 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los campos");
        UserResponse userResponse = authService.register(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @Override
    public ResponseEntity<?> refreshToken(@Valid AuthResponse authResponse, BindingResult result) throws ParseException {
        if (result.hasErrors()) 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los campos");
        AuthResponse authResponseRefreshToken = authService.refreshToken(authResponse);
        return ResponseEntity.status(HttpStatus.OK).body(authResponseRefreshToken);
    }

}
