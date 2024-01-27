package com.store.shopping_cart.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;

@Service
public interface OrderDetailService {

    List<OrderDetailDto> getDetailsByOrder(int idOrder);

    OrderDetailDto getDetailById(int idOrderDetail);

    OrderDetailDto saveOrderDetail(OrderDetailDto orderDetailDto);

    boolean deleteDetail(int idOrderDetail);

}
