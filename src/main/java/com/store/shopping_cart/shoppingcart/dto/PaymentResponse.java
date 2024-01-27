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
public class PaymentResponse {

    private OrderResponse idOrder;
    private String paymentType;
    private String status;
    private Date creatDate;
    private Date updateDate;

}
