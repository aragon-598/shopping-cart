package com.store.shopping_cart.shoppingcart.dto;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductResponse extends RepresentationModel<ProductResponse>  {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("title")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image")
    private String imageUrl;

    @JsonProperty("price")
    private BigDecimal price = null;
    
}
