package com.store.shopping_cart.shoppingcart.controller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.store.shopping_cart.shoppingcart.controller.PaymentController;
import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.dto.PaymentRequest;
import com.store.shopping_cart.shoppingcart.dto.PaymentResponse;
import com.store.shopping_cart.shoppingcart.service.PaymentService;

@RestController
public class PaymentControllerImp  implements PaymentController{

    @Autowired
    private PaymentService paymentService;

    @Override
    public ResponseEntity<PaymentResponse> createPayment(PaymentRequest paymentRequest) {
        PaymentResponse response = paymentService.savePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<PaymentResponse> findPaymentByOrder(OrderResponse idOrder) {
        PaymentResponse response = paymentService.getPaymentByOrder(idOrder);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<PaymentResponse> updatePayment(PaymentRequest paymentRequest) {
        PaymentResponse response = paymentService.savePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
