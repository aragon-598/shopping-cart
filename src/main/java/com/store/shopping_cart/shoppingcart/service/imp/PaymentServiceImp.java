package com.store.shopping_cart.shoppingcart.service.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.dto.PaymentRequest;
import com.store.shopping_cart.shoppingcart.dto.PaymentResponse;
import com.store.shopping_cart.shoppingcart.exception.InvalidParameterValueException;
import com.store.shopping_cart.shoppingcart.exception.ResourceNotFoundException;
import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.models.Payment;
import com.store.shopping_cart.shoppingcart.models.PaymentStatus;
import com.store.shopping_cart.shoppingcart.models.PaymentType;
import com.store.shopping_cart.shoppingcart.repository.PaymentRepository;
import com.store.shopping_cart.shoppingcart.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImp implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PaymentResponse getPaymentById(int idPayment) {
        Payment payment = paymentRepository.findById(idPayment).orElseThrow(() -> new ResourceNotFoundException(String.format(" Payment not exists - %s", idPayment)));
        PaymentResponse paymentResponse = modelMapper.map(payment, PaymentResponse.class);
        return paymentResponse;
    }

    @Override
    public PaymentResponse getPaymentByOrder(int idOrder) {
        Payment payment = paymentRepository.findByIdOrder(idOrder).orElseThrow(() -> new ResourceNotFoundException(String.format(" Payment not exists to order with id - %s",idOrder)));
        PaymentResponse paymentResponse = modelMapper.map(payment, PaymentResponse.class);

        return paymentResponse;
    }

    @Override
    public PaymentResponse getPaymentByOrder(Order idOrder) {
        if (idOrder == null) {
            throw new InvalidParameterValueException(String.format("Invalid value. Null is not valid value - %s", idOrder));
        }
        
        Payment payment = paymentRepository.findByIdOrder(idOrder).orElseThrow(() -> new ResourceNotFoundException(String.format(" Payment not exists to order with id - %s",idOrder)));
        PaymentResponse paymentResponse = modelMapper.map(payment, PaymentResponse.class);
        return paymentResponse;
    }

    @Override
    public PaymentResponse savePayment(PaymentRequest paymentRequest) {

        if (paymentRequest == null) {
            throw new InvalidParameterValueException(String.format("Invalid value. Null is not valid value - %s", paymentRequest));
        }

        Payment payment = modelMapper.map(paymentRequest, Payment.class);
        payment.setPaymentType(setPaymentType(paymentRequest.getPaymentType()));
        payment.setStatus(setPaymentStatus(paymentRequest.getStatus()));
        if (payment.getStatus().name().equals("")) {
            
        }
        
        PaymentResponse paymentResponse = modelMapper.map(paymentRepository.save(payment), PaymentResponse.class);

        return paymentResponse;
    }

    @Override
    public PaymentStatus setPaymentStatus(String status) {
        PaymentStatus newStatus =  null;

        switch (status) {
            case "pending":
                newStatus= PaymentStatus.PENDING;
                break;
            case "completed":
                newStatus= PaymentStatus.COMPLETED;
                break;
            case "declined":
                newStatus= PaymentStatus.DECLINED;
                break;
            case "canceled":
                newStatus= PaymentStatus.CANCELED;
                break;
        }

        return newStatus;
    }

    public PaymentType setPaymentType(String type) {
        PaymentType newType =  null;

        switch (type) {
            case "credit":
                newType= PaymentType.CREDIT_CARD;
                break;
            case "debit":
                newType= PaymentType.DEBIT_CARD;
                break;
            case "transfer":
                newType= PaymentType.TRANSFER;
                break;
            case "cash":
                newType= PaymentType.CASH;
                break;
        }

        return newType;
    }

}
