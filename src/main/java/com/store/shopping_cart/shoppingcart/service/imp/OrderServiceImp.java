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
import com.store.shopping_cart.shoppingcart.exception.InvalidParameterValueException;
import com.store.shopping_cart.shoppingcart.exception.ResourceNotFoundException;
import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.models.OrderDetail;
import com.store.shopping_cart.shoppingcart.models.OrderStatus;
import com.store.shopping_cart.shoppingcart.repository.OrderRepository;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;
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

        
        if (orderRequest == null) {
            throw new InvalidParameterValueException(String.format("Invalid value. Null is not valid value - %s", orderRequest));
        }

        UserResponse userResponse = orderRequest.getIdUser();
        System.out.println(orderRequest.toString());
        Order order = modelMapper.map(orderRequest,Order.class);
        order.setStatus(changeOrderStatus(orderRequest.getStatus()));
        Order orderAux = repository.save(order);

        OrderResponse orderResponse = modelMapper.map(
                                                    orderAux,
                                                    OrderResponse.class 
                                                    );
        UserResponse userResponseAux = orderAux.getIdUser() != null 
                                                ? modelMapper.map(orderAux.getIdUser(), UserResponse.class) 
                                                : null;

        orderResponse.setIdUser(userResponse);
        orderResponse.setIdUser(userResponseAux);
        return orderResponse;
    }

    @Override
    public OrderResponse getOrderById(int idOrder) {
        Order order = repository.findById(idOrder).orElseThrow(() -> new ResourceNotFoundException(String.format(" - %s", idOrder)));

        return modelMapper.map(order,OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getOrderByUsuario(UserResponse userResponse) {


        if (userResponse == null) {
            throw new InvalidParameterValueException(String.format("Invalid value. Null is not valid value - %s", userResponse));
        }

        User idUser = userRepository.findByUsername(userResponse.getUsername()).orElseThrow(() -> new ResourceNotFoundException(String.format(" - %s", userResponse.getUsername())));
        List<Order> orderList = repository.findByIdUser(idUser).orElse(new ArrayList<>());

        List<OrderResponse> orderResponseList = orderList.stream()
                                                            .map(
                                                                order -> {
                                                                    OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
                                                                    UserResponse userResponseAux = modelMapper.map(order.getIdUser(), UserResponse.class);

                                                                    orderResponse.setIdUser(userResponseAux);

                                                                    return orderResponse;
                                                                }
                                                            )
                                                            .collect(Collectors.toList());

        return orderResponseList;
    }

    @Override
    public OrderStatus changeOrderStatus(String orderStatus) {
        OrderStatus newStatus =  null;

        switch (orderStatus.toLowerCase()) {
            case "in_progress":
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

    @Override
    public void updateOrderTotal(List<OrderDetail> detailsList, Order order) {
        double totalAmount = detailsList
                                    .stream()
                                    .mapToDouble(detail -> detail.getAmount())
                                    .sum();
        int quantity = detailsList
                            .stream()
                            .mapToInt(detail -> detail.getQuantity())
                            .sum();
        order.setTotalItems(quantity);
        order.setTotalAmount(totalAmount);
        repository.save(order);
    }

    @Override
    public OrderResponse setOrderStatusByPayment(String paymentStatus, int idOrder) {
        //PENDING, COMPLETED, DECLINED, CANCELED, REFUND;
        OrderStatus newStatus = null;
        paymentStatus = paymentStatus.toLowerCase();
        switch (paymentStatus) {
            case "pending":
                newStatus= OrderStatus.OUTSTANDING;
                break;
            case "completed":
                newStatus= OrderStatus.COMPLETED;
                break;
            case "declined":
                newStatus= OrderStatus.OUTSTANDING;
                break;
            case "canceled":
                newStatus= OrderStatus.CANCELED;
                break;
            case "refund":
                newStatus= OrderStatus.OUTSTANDING;
                break;
        }

        Order orderEntity = repository.findById(idOrder).orElseThrow();
        orderEntity.setStatus(newStatus);
        OrderResponse orderResponse =  modelMapper.map(orderEntity, OrderResponse.class);
        UserResponse userResponse = modelMapper.map(orderEntity.getIdUser(), UserResponse.class);

        orderResponse.setIdUser(userResponse);

        return orderResponse;
    }

}
