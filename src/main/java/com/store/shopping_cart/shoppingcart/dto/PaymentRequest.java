package com.store.shopping_cart.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class PaymentRequest {
    private Integer idPayment;
    private OrderRequest idOrder;
    private String paymentType;
    private String status;
}
