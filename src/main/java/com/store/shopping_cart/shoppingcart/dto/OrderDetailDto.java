package com.store.shopping_cart.shoppingcart.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class OrderDetailDto {
    private Integer idOrderDetail;
    private int quantity;
    private double amount;
    private ProductResponse productId;
    private double productPrice;
    private OrderResponse idOrder;
}
