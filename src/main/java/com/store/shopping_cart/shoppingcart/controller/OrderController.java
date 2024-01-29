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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "orders API", description = "orders endpoints")
@RequestMapping("/api/v1/orders")
public interface OrderController {
    
    @Operation(description = "Return all orders by user")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="Success")})
    @GetMapping(produces = {"application/json"})
    default ResponseEntity<List<OrderResponse>> findOrderByUser(@RequestBody UserResponse user){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(description = "Save order information", summary = "Return 201 if data is good")
    @ApiResponses(value = {@ApiResponse(responseCode="201",description="Succeded")})
    @PostMapping(produces = {"application/json"})
    default ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest order){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(description = "Update order information", summary = "Return 404 if the order not exists")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="successful")})
    @PutMapping(produces = {"application/json"})
    default ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderRequest order){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
