package com.store.shopping_cart.shoppingcart.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.dto.OrderRequest;
import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;
import com.store.shopping_cart.shoppingcart.service.OrderService;

@Service
@Transactional
public class OrderServiceImp implements OrderService {

    @Override
    public OrderResponse saveOrder(OrderRequest order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveOrder'");
    }

    @Override
    public OrderResponse getOrderById(int idOrder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderById'");
    }

    @Override
    public List<OrderResponse> getOrderByUsuario(UserRequest user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderByUsuario'");
    }

    @Override
    public OrderResponse changeOrderStatus(OrderRequest order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeOrderStatus'");
    }

}
