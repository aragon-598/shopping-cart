package com.store.shopping_cart.shoppingcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.shopping_cart.shoppingcart.dto.ProductResponse;

@RequestMapping("/api/v1/products")
public interface ProductController {
    
    @GetMapping(produces = { "application/json" })
    default ResponseEntity<List<ProductResponse>> findAll() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(path = "/{id}", produces = { "application/json" })
    default ResponseEntity<ProductResponse> findById(@PathVariable("id") int id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
