package com.store.shopping_cart.shoppingcart.service.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.dto.PaymentRequest;
import com.store.shopping_cart.shoppingcart.dto.PaymentResponse;
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
        PaymentResponse payment = modelMapper.map(paymentRepository.findById(idPayment).orElse(null), PaymentResponse.class);
        return payment;
    }

    @Override
    public PaymentResponse getPaymentByOrder(int idOrder) {
        PaymentResponse payment = modelMapper.map(paymentRepository.findByIdOrder(idOrder).orElse(null), PaymentResponse.class);

        return payment;
    }

    @Override
    public PaymentResponse getPaymentByOrder(Order idOrder) {
        PaymentResponse payment = modelMapper.map(paymentRepository.findByIdOrder(idOrder).orElse(null), PaymentResponse.class);
        return payment;
    }

    @Override
    public PaymentResponse savePayment(PaymentRequest payment) {
        Payment newPayment = modelMapper.map(payment, Payment.class);
        newPayment.setPaymentType(setPaymentType(payment.getPaymentType()));
        newPayment.setStatus(setPaymentStatus(payment.getStatus()));
        
        PaymentResponse paymentResponse = modelMapper.map(paymentRepository.save(newPayment), PaymentResponse.class);

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
