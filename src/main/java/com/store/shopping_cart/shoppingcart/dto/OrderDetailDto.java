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
    private int quantity;
    private double amount;
    private int idProduct;
    private double productPrice;
    private OrderRequest idOrder;
}
