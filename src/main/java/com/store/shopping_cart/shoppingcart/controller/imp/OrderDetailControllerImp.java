package com.store.shopping_cart.shoppingcart.controller.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.store.shopping_cart.shoppingcart.controller.OrderDetailController;
import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;
import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.service.OrderDetailService;

@RestController
public class OrderDetailControllerImp implements OrderDetailController {


    @Autowired
    OrderDetailService oDetailService;


    @Override
    public ResponseEntity<?> createOrderDetail(OrderDetailDto orderDetail, BindingResult result) {
        if (result.hasErrors()) 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en los campos");
        OrderDetailDto response = oDetailService.saveOrderDetail(orderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<?> deletedOrderDetail(int idOrderDetail) {
        oDetailService.deleteDetail(idOrderDetail);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @Override
    public ResponseEntity<List<?>> findOrderDetailByOrder(OrderResponse idOrder, BindingResult result) {
        List<OrderDetailDto> response = oDetailService.getDetailsByOrder(idOrder);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Override
    public ResponseEntity<?> updateOrderDetail(int idOrderDetail, OrderDetailDto orderDetail, BindingResult result) {
        orderDetail.setIdOrderDetail(idOrderDetail);
        OrderDetailDto response = oDetailService.saveOrderDetail(orderDetail);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> findOrderDetailById(int idOrderDetail) {
        OrderDetailDto response = oDetailService.getDetailById(idOrderDetail);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
