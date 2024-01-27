package com.store.shopping_cart.shoppingcart.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class OrderResponse {

    private double totalAmount;
    private int totalItems;
    private String status;
    private PaymentResponse idPayment;
    private Date creatDate;
    private Date updateDate;

}
