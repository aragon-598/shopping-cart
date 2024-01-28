package com.store.shopping_cart.shoppingcart.controller.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.store.shopping_cart.shoppingcart.controller.OrderController;
import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;
import com.store.shopping_cart.shoppingcart.dto.OrderRequest;
import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;
import com.store.shopping_cart.shoppingcart.service.OrderDetailService;
import com.store.shopping_cart.shoppingcart.service.OrderService;

@RestController
public class OrderControllerImp implements OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService oDetailService;

    @Override
    public ResponseEntity<OrderResponse> createOrder(OrderRequest order) {
        OrderResponse response = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<OrderDetailDto> createOrderDetail(OrderDetailDto orderDetail) {
        OrderDetailDto response = oDetailService.saveOrderDetail(orderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> deletedOrderDetail(int idOrderDetail) {
        oDetailService.deleteDetail(idOrderDetail);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @Override
    public ResponseEntity<List<OrderResponse>> findOrderByUser(UserResponse user) {
        List<OrderResponse> response = orderService.getOrderByUsuario(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<List<OrderDetailDto>> findOrderDetailByOrder(Order idOrder) {
        List<OrderDetailDto> response = oDetailService.getDetailsByOrder(idOrder);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<OrderResponse> updateOrder(OrderRequest order) {
        OrderResponse response = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<OrderDetailDto> updateOrderDetail(OrderDetailDto orderDetail) {
        OrderDetailDto response = oDetailService.saveOrderDetail(orderDetail);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<OrderDetailDto> findOrderDetailById(int idOrderDetail) {
        OrderDetailDto response = oDetailService.getDetailById(idOrderDetail);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
