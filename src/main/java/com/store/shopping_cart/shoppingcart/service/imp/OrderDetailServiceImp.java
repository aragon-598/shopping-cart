package com.store.shopping_cart.shoppingcart.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;
import com.store.shopping_cart.shoppingcart.dto.OrderResponse;
import com.store.shopping_cart.shoppingcart.dto.ProductResponse;
import com.store.shopping_cart.shoppingcart.exception.InvalidParameterValueException;
import com.store.shopping_cart.shoppingcart.exception.ResourceNotFoundException;
import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.models.OrderDetail;
import com.store.shopping_cart.shoppingcart.repository.OrderDetailRepository;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;
import com.store.shopping_cart.shoppingcart.service.OrderDetailService;
import com.store.shopping_cart.shoppingcart.service.OrderService;
import com.store.shopping_cart.shoppingcart.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class OrderDetailServiceImp implements OrderDetailService {

    @Autowired
    private OrderDetailRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Override
    public List<OrderDetailDto> getDetailsByOrder(OrderResponse idOrder) {
        Order orderEntity = modelMapper.map(idOrder, Order.class);
        List<OrderDetail> detailsList = repository.findByIdOrder(orderEntity).orElse(new ArrayList<OrderDetail>());

        List<OrderDetailDto> detailsDtoList = detailsList.stream().map(
                                                                  orderDetail -> {
                                                                    int idProduct = orderDetail.getIdProduct();
                                                                    OrderDetailDto detailDto = modelMapper.map(orderDetail, OrderDetailDto.class);
                                                                    UserResponse userResponse = modelMapper.map(orderDetail.getIdOrder().getIdUser(),  UserResponse.class);
                                                                    detailDto.getIdOrder().setIdUser(userResponse);
                                                                    detailDto.setProductId(getProduct(idProduct));
                                                                    return detailDto;
                                                                  }
                                                                ).collect(Collectors.toList());

        return detailsDtoList;

    }

    @Override
    public OrderDetailDto getDetailById(int idOrderDetail) {
        OrderDetail orderDetail = repository.findById(idOrderDetail).orElseThrow(() -> new ResourceNotFoundException(String.format("Not found detail with id - %s", idOrderDetail)));
        OrderDetailDto orderDetailDto = modelMapper.map( orderDetail ,OrderDetailDto.class);
        orderDetailDto.setProductId(getProduct(orderDetail.getIdProduct()));

        return orderDetailDto;
    }

    @Override
    public OrderDetailDto saveOrderDetail(OrderDetailDto orderDetailDto) {
        if (orderDetailDto == null) {
            throw new InvalidParameterValueException(String.format("Invalid value. Null is not valid value - %s", orderDetailDto));
        }
        //extrayendo el idProduct para setear a la entidad
        int idProduct = orderDetailDto.getProductId().getProductId();

        double productPrice = orderDetailDto.getProductId().getPrice();
        int quantity = orderDetailDto.getQuantity();

        //calculando el amount del detalle
        double amount = quantity * productPrice;

        OrderDetail orderDetail = modelMapper.map(orderDetailDto, OrderDetail.class);

        //seteando en la entidad producto, cantidad y total del detalle
        orderDetail.setIdProduct(idProduct);
        orderDetail.setQuantity(quantity);
        orderDetail.setAmount(amount);
        orderDetail.setProductPrice(productPrice);

        //guardando la entidad
        OrderDetail orderDetailSaved = repository.save(orderDetail);
        OrderDetailDto response = modelMapper.map( orderDetailSaved, OrderDetailDto.class);
        
        //recalculando totalAmount y totalItems en orden padre
        List<OrderDetail> detailsList = repository.findByIdOrder(orderDetailSaved.getIdOrder()).orElse(new ArrayList<OrderDetail>());
        orderService.updateOrderTotal( detailsList ,orderDetailSaved.getIdOrder());

        //Seteando user reponse para no mostrar null y el precio del producto no aparezca en 0
        UserResponse userResponse = orderDetail.getIdOrder().getIdUser() != null 
                                                ? modelMapper.map(orderDetail.getIdOrder().getIdUser(), UserResponse.class) 
                                                : null;
        response.getIdOrder().setIdUser(userResponse);
        response.setProductId(orderDetailDto.getProductId());

        return response;
    }

    @Override
    public boolean deleteDetail(int idOrderDetail) {
        log.info("METODO deleteDetail ID-->["+idOrderDetail+"] DE LA CLASE "+getClass().getName());
        OrderDetail exists = repository.findById(idOrderDetail).orElseThrow(() -> new ResourceNotFoundException(String.format("Not found detail to delete with id - %s", idOrderDetail)));
        log.info("SE VERIFICO SI EXISTE EL DETALLE EN LA CLASE "+getClass().getName());
        
        if (exists != null) {
            log.info("EL DETALLE SI EXISTE EN LA CLASE "+getClass().getName());
            repository.deleteById(idOrderDetail);
            List<OrderDetail> detailsList = repository.findByIdOrder(exists.getIdOrder()).orElse(new ArrayList<OrderDetail>());
            orderService.updateOrderTotal( detailsList ,exists.getIdOrder());
            return true;
        }

        return false;
    }

    @Override
    public ProductResponse getProduct(int idProduct) {
        return productService.getASingleProduct(idProduct);
    }

}
