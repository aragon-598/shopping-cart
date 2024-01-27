package com.store.shopping_cart.shoppingcart.controller.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.store.shopping_cart.shoppingcart.controller.ProductController;
import com.store.shopping_cart.shoppingcart.dto.ProductResponse;
import com.store.shopping_cart.shoppingcart.service.ProductService;

@RestController
public class ProductControllerImp implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Override
    public ResponseEntity<ProductResponse> findById(int id) {
        return ResponseEntity.ok(productService.getASingleProduct(id));
    }
    
}
