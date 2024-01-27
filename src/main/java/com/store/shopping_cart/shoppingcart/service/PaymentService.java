package com.store.shopping_cart.shoppingcart.service;

import org.springframework.stereotype.Service;

import com.store.shopping_cart.shoppingcart.dto.PaymentRequest;
import com.store.shopping_cart.shoppingcart.dto.PaymentResponse;
import com.store.shopping_cart.shoppingcart.models.Order;

@Service
public interface PaymentService {

    PaymentResponse getPaymentById(int idPayment);

    PaymentResponse getPaymentByOrder(int idOrder);

    PaymentResponse getPaymentByOrder(Order idOrder);

    PaymentResponse savePayment(PaymentRequest payment);

    PaymentResponse changePaymentStatus(PaymentRequest payment);

}
