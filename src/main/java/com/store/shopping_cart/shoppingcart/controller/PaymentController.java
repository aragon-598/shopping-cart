package com.store.shopping_cart.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.dto.PaymentRequest;
import com.store.shopping_cart.shoppingcart.dto.PaymentResponse;

@RequestMapping("/api/v1/payments")
public interface PaymentController {

        @GetMapping( path = "/by-order", produces = {"application/json"})
    default ResponseEntity<PaymentResponse> findPaymentByOrder(@RequestBody OrderResponse idOrder){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(produces = {"application/json"})
    default ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(produces = {"application/json"})
    default ResponseEntity<PaymentResponse> updatePayment(@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


}
