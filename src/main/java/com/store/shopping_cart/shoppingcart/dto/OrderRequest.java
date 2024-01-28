package com.store.shopping_cart.shoppingcart.dto;


import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class OrderRequest {
    private Integer idOrder;
    private double totalAmount;
    private int totalItems;
    private String status;
    private UserResponse idUser;
}
