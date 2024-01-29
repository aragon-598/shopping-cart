package com.store.shopping_cart.shoppingcart.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.shopping_cart.shoppingcart.security.dto.LoginRequest;
import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "auth API", description = "auth endpoints")
@RequestMapping("/auth")
public interface AuthController {
    
    @Operation(description = "login user")
    @ApiResponses(value = {@ApiResponse(responseCode="201",description="Succeded")})
    @PostMapping("/login")
    default ResponseEntity<?> login(@Valid @RequestBody LoginRequest login, BindingResult result) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    @Operation(description = "Save user information", summary = "Return 201 if data is good")
    @ApiResponses(value = {@ApiResponse(responseCode="201",description="Succeded")})
    @PostMapping("/register")
    default ResponseEntity<?> register(@Valid @RequestBody UserRequest user, BindingResult result) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
