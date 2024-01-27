package com.store.shopping_cart.shoppingcart.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;
import com.store.shopping_cart.shoppingcart.exception.ProductNotFoundException;
import com.store.shopping_cart.shoppingcart.models.OrderDetail;
import com.store.shopping_cart.shoppingcart.repository.OrderDetailRepository;
import com.store.shopping_cart.shoppingcart.service.OrderDetailService;

@Service
@Transactional
public class OrderDetailServiceImp implements OrderDetailService {

    @Autowired
    private OrderDetailRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderDetailDto> getDetailsByOrder(int idOrder) {
        List<OrderDetail> detailsList = repository.findByIdOrder(idOrder).orElse(new ArrayList<OrderDetail>());

        List<OrderDetailDto> detailsDtoList = detailsList.stream().map(
                                                                  orderDetail -> modelMapper.map(orderDetail, OrderDetailDto.class)
                                                                ).collect(Collectors.toList());

        return detailsDtoList;
    }

    @Override
    public OrderDetailDto getDetailById(int idOrderDetail) {
        OrderDetail orderDetail = repository.findById(idOrderDetail).orElseThrow(() -> new ProductNotFoundException(String.format(" - %s", idOrderDetail)));
        OrderDetailDto orderDetailDto = modelMapper.map( orderDetail ,OrderDetailDto.class);

        return orderDetailDto;
    }

    @Override
    public OrderDetailDto saveOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = modelMapper.map(orderDetailDto, OrderDetail.class);

        OrderDetailDto response = modelMapper.map(repository.save(orderDetail), OrderDetailDto.class);

        return response;
    }

    @Override
    public boolean deleteDetail(int idOrderDetail) {
        boolean exists = repository.existsById(idOrderDetail);

        if (exists) {
            repository.deleteById(idOrderDetail);
            return true;
        }

        return false;
    }

}
