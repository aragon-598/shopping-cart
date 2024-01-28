package com.store.shopping_cart.shoppingcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;
import com.store.shopping_cart.shoppingcart.models.Order;

@RequestMapping("/api/v1/order-detail")
public interface OrderDetailController {
    
    @GetMapping( path = "/by-order", produces = {"application/json"})
    default ResponseEntity<List<OrderDetailDto>> findOrderDetailByOrder(@RequestBody Order idOrder){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    @GetMapping( path = "/{idOrderDetail}", produces = {"application/json"})
    default ResponseEntity<OrderDetailDto> findOrderDetailById(@PathVariable int idOrderDetail){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
   @PostMapping(path = "", produces = {"application/json"})
    default ResponseEntity<OrderDetailDto> createOrderDetail(@RequestBody OrderDetailDto orderDetail){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(path = "", produces = {"application/json"})
    default ResponseEntity<OrderDetailDto> updateOrderDetail(@RequestBody OrderDetailDto orderDetail){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @DeleteMapping(path = "/{idOrderDetail}", produces = {"application/json"})
    default ResponseEntity<String> deletedOrderDetail(@PathVariable int idOrderDetail){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
