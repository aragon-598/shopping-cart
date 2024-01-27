package com.store.shopping_cart.shoppingcart.service;

import java.util.List;

import com.store.shopping_cart.shoppingcart.dto.ProductResponse;

public interface ProductService {
    
    List<ProductResponse> getAllProducts();

    ProductResponse getASingleProduct(Integer id);

}
