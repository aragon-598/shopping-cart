package com.store.shopping_cart.shoppingcart.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;
import com.store.shopping_cart.shoppingcart.service.OrderDetailService;

@Service
@Transactional
public class OrderDetailServiceImp implements OrderDetailService {

    @Override
    public List<OrderDetailDto> getDetailsByOrder(int idOrder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDetailsByOrder'");
    }

    @Override
    public OrderDetailDto getDetailById(int idOrderDetail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDetailById'");
    }

    @Override
    public OrderDetailDto saveOrderDetail(OrderDetailDto order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveOrderDetail'");
    }

    @Override
    public boolean deleteDetail(int idOrderDetail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDetail'");
    }

}
