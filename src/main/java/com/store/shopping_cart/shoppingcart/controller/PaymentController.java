package com.store.shopping_cart.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.dto.PaymentRequest;

import jakarta.validation.Valid;

@Validated
@RequestMapping("/api/v1/payments")
public interface PaymentController {

    /**
     * get payment by order
     * @param idOrder
     * @param result
     * @return
     */
    @GetMapping( path = "/by-order", produces = {"application/json"})
    default ResponseEntity<?> findPaymentByOrder(  @Valid @RequestBody OrderResponse idOrder, BindingResult result){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * create payment of order
     * @param paymentRequest
     * @param result
     * @return
     */
    @PostMapping(produces = {"application/json"})
    default ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequest paymentRequest, BindingResult result){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * update payment
     * @param paymentRequest
     * @param result
     * @return
     */
    @PutMapping(produces = {"application/json"})
    default ResponseEntity<?> updatePayment(@Valid @RequestBody PaymentRequest paymentRequest, BindingResult result){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


}
