package com.store.shopping_cart.shoppingcart.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.store.shopping_cart.shoppingcart.dto.ProductResponse;

@FeignClient(name = "products", url = "https://fakestoreapi.com", path = "/products")
public interface ProductServiceClient {

    @GetMapping
    List<ProductResponse> findAllProducts();

    @GetMapping("/{id}")
    ProductResponse getASingleProduct(@PathVariable(name = "id") Integer id);


}
