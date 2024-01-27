package com.store.shopping_cart.shoppingcart.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.shopping_cart.shoppingcart.client.ProductServiceClient;
import com.store.shopping_cart.shoppingcart.dto.ProductResponse;
import com.store.shopping_cart.shoppingcart.exception.ProductNotFoundException;
import com.store.shopping_cart.shoppingcart.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productServiceClient.findAllProducts();
    }

    @Override
    public ProductResponse getASingleProduct(Integer id) {
        return Optional.ofNullable(productServiceClient.getASingleProduct(id)).orElseThrow(() -> new ProductNotFoundException(String.format(" - %s", id)));
    }
    
}
