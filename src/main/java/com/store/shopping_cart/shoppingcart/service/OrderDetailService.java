package com.store.shopping_cart.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;
import com.store.shopping_cart.shoppingcart.dto.ProductResponse;
import com.store.shopping_cart.shoppingcart.models.Order;

@Service
public interface OrderDetailService {

    List<OrderDetailDto> getDetailsByOrder(int idOrder);

    List<OrderDetailDto> getDetailsByOrder(Order idOrder);

    OrderDetailDto getDetailById(int idOrderDetail);

    OrderDetailDto saveOrderDetail(OrderDetailDto orderDetailDto);

    boolean deleteDetail(int idOrderDetail);

    ProductResponse getProduct(int idProduct);

}
