package com.store.shopping_cart.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.shopping_cart.shoppingcart.dto.OrderRequest;
import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;

@Service
public interface OrderService {

    OrderResponse saveOrder(OrderRequest order);
    OrderResponse getOrderById(int idOrder);
    List<OrderResponse> getOrderByUsuario(UserRequest user);
    OrderResponse changeOrderStatus(OrderRequest order);


}
