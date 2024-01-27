package com.store.shopping_cart.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class OrderRequest {
    private double totalAmount;
    private int totalItems;
    private String status;
    private PaymentRequest idPayment;
}
