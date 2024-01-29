package com.store.shopping_cart.shoppingcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.shopping_cart.shoppingcart.dto.ProductResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Product API", description = "Product endpoints")
@RequestMapping("/api/v1/products")
public interface ProductController {
    
    /**
     * get all products
     * @return
     */
    @Operation(description = "Return all products")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="Success")})
    @GetMapping(produces = { "application/json" })
    default ResponseEntity<List<ProductResponse>> findAll() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * get product by id
     * @param id
     * @return
     */
    @Operation(description = "Return product by ID", summary = "Return 404 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="successful")})
    @GetMapping(path = "/{id}", produces = { "application/json" })
    default ResponseEntity<ProductResponse> findById(@PathVariable("id") int id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
