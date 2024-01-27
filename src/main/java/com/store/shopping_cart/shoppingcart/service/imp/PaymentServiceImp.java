package com.store.shopping_cart.shoppingcart.service.imp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.dto.PaymentRequest;
import com.store.shopping_cart.shoppingcart.dto.PaymentResponse;
import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImp implements PaymentService {

    @Override
    public PaymentResponse getPaymentById(int idPayment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentById'");
    }

    @Override
    public PaymentResponse getPaymentByOrder(int idOrder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentByOrder'");
    }

    @Override
    public PaymentResponse getPaymentByOrder(Order idOrder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentByOrder'");
    }

    @Override
    public PaymentResponse savePayment(PaymentRequest payment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePayment'");
    }

    @Override
    public PaymentResponse changePaymentStatus(PaymentRequest payment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePaymentStatus'");
    }

}
