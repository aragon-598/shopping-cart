package com.store.shopping_cart.shoppingcart.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.dto.OrderRequest;
import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.exception.ProductNotFoundException;
import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.models.OrderStatus;
import com.store.shopping_cart.shoppingcart.repository.OrderRepository;
import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;
import com.store.shopping_cart.shoppingcart.security.models.User;
import com.store.shopping_cart.shoppingcart.security.repository.UserRepository;
import com.store.shopping_cart.shoppingcart.service.OrderService;

@Service
@Transactional
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {

        Order order = modelMapper.map(orderRequest,Order.class);
        order.setStatus(changeOrderStatus(orderRequest.getStatus()));

        OrderResponse orderResponse = modelMapper.map(
                                                    repository.save(order), 
                                                    OrderResponse.class 
                                                    );

        return orderResponse;
    }

    @Override
    public OrderResponse getOrderById(int idOrder) {
        Order order = repository.findById(idOrder).orElseThrow(() -> new ProductNotFoundException(String.format(" - %s", idOrder)));

        return modelMapper.map(order,OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getOrderByUsuario(UserRequest userRequest) {
        User idUser = userRepository.findByUsername(userRequest.getUsername()).orElseThrow(() -> new ProductNotFoundException(String.format(" - %s", userRequest.getUsername())));
        List<Order> orderList = repository.findByIdUser(idUser).orElse(new ArrayList<>());

        List<OrderResponse> orderResponseList = orderList.stream()
                                                            .map(
                                                                order -> modelMapper.map(order, OrderResponse.class)
                                                            )
                                                            .collect(Collectors.toList());

        return orderResponseList;
    }

    @Override
    public OrderStatus changeOrderStatus(String orderStatus) {
        OrderStatus newStatus =  null;

        switch (orderStatus) {
            case "in progress":
                newStatus= OrderStatus.IN_PROGRESS;
                break;
            case "completed":
                newStatus= OrderStatus.COMPLETED;
                break;
            case "canceled":
                newStatus= OrderStatus.CANCELED;
                break;
            case "outstanding":
                newStatus= OrderStatus.OUTSTANDING;
                break;
        }

        return newStatus;
    }

}
