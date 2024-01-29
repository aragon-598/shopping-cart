package com.store.shopping_cart.shoppingcart.controller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> createPayment(PaymentRequest paymentRequest, BindingResult result) {
        if (result.hasErrors()) 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los campos");
        PaymentResponse response = paymentService.savePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<?> findPaymentByOrder(OrderResponse idOrder, BindingResult result) {
        if (result.hasErrors()) 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los campos");
        PaymentResponse response = paymentService.getPaymentByOrder(idOrder);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> updatePayment(PaymentRequest paymentRequest, BindingResult result) {
        if (result.hasErrors()) 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los campos");
        PaymentResponse response = paymentService.savePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
