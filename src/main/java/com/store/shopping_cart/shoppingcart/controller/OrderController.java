package com.store.shopping_cart.shoppingcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.shopping_cart.shoppingcart.dto.OrderRequest;
import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;

@RequestMapping("/api/v1/orders")
public interface OrderController {
    
    @GetMapping(produces = {"application/json"})
    default ResponseEntity<List<OrderResponse>> findOrderByUser(@RequestBody UserResponse user){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(produces = {"application/json"})
    default ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest order){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(produces = {"application/json"})
    default ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderRequest order){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
