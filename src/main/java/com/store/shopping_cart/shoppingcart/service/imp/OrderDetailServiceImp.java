package com.store.shopping_cart.shoppingcart.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;
import com.store.shopping_cart.shoppingcart.dto.ProductResponse;
import com.store.shopping_cart.shoppingcart.exception.InvalidParameterValueException;
import com.store.shopping_cart.shoppingcart.exception.ResourceNotFoundException;
import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.models.OrderDetail;
import com.store.shopping_cart.shoppingcart.repository.OrderDetailRepository;
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
    public List<OrderDetailDto> getDetailsByOrder(int idOrder) {
        log.info("ENTRE AL METODO DE DETAILS BY ORDER ------->",idOrder);
        System.out.println("ID PARA BUSCAR POR ORDEN {"+idOrder+"} ");
        List<OrderDetail> detailsList = repository.findByIdOrder(idOrder).orElse(new ArrayList<OrderDetail>());
        log.info("DESPUES DE IR A LA DB METODO DE DETAILS BY ORDER");

        List<OrderDetailDto> detailsDtoList = detailsList.stream().map(
                                                                  orderDetail -> {
                                                                    int idProduct = orderDetail.getIdProduct();
                                                                    OrderDetailDto detailDto = modelMapper.map(orderDetail, OrderDetailDto.class);
                                                                    detailDto.setProductId(getProduct(idProduct));
                                                                    return detailDto;
                                                                  }
                                                                ).collect(Collectors.toList());

        return detailsDtoList;
    }

    @Override
    public List<OrderDetailDto> getDetailsByOrder(Order idOrder) {
        List<OrderDetail> detailsList = repository.findByIdOrder(idOrder).orElse(new ArrayList<OrderDetail>());

        List<OrderDetailDto> detailsDtoList = detailsList.stream().map(
                                                                  orderDetail -> {
                                                                    int idProduct = orderDetail.getIdProduct();
                                                                    OrderDetailDto detailDto = modelMapper.map(orderDetail, OrderDetailDto.class);
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
        orderDetail.setIdProduct(idProduct);
        orderDetail.setQuantity(quantity);
        orderDetail.setAmount(amount);
        OrderDetail orderDetailSaved = repository.save(orderDetail);
        OrderDetailDto response = modelMapper.map( orderDetailSaved, OrderDetailDto.class);
        
        //recalculando totalAmount y totalItems en orden padre
        List<OrderDetail> detailsList = repository.findByIdOrder(orderDetailSaved.getIdOrder()).orElse(new ArrayList<OrderDetail>());
        orderService.updateOrderTotal( detailsList ,orderDetailSaved.getIdOrder());

        response.setProductId(orderDetailDto.getProductId());

        return response;
    }

    @Override
    public boolean deleteDetail(int idOrderDetail) {
        boolean exists = repository.existsById(idOrderDetail);

        if (exists) {
            repository.deleteById(idOrderDetail);
            Order order = modelMapper.map(orderService.getOrderById(idOrderDetail), Order.class);
            List<OrderDetail> detailsList = repository.findByIdOrder(order).orElse(new ArrayList<OrderDetail>());
            orderService.updateOrderTotal( detailsList ,order);
            return true;
        }

        return false;
    }

    @Override
    public ProductResponse getProduct(int idProduct) {
        return productService.getASingleProduct(idProduct);
    }

    //TODO: METODO DE ACTUALIZAR
    /*
        *IDEAS: 
        - EN BASE A LA ORDEN DEL DETALLE TRAER UNA LISTA DE DETALLES, 
          TOMAR EL ORDERDETAIL QUE SEA SIMILAR AL QUE SE ESTA MODIFICANDO
          Y RESTAR LOS AMOUNT EL RESULTADO YA SEA MAYOR O NEGATIVO SE SUMA
          AL DE LA ORDEN
        - TOMAR EN CUENTA QUE EL AMOUNT SE CALCULA EN BASE A LOS QUATINTY
        - AL MODIFICAR EL QUANTITY DEL DETALLE SE DEBE MODIFICAR EL DE LA ORDEN PADRE
          UNA IDEA PARA ESTE ES DE FORMA SIMILAR AL AMOUNT, RESTAR AMBOS Y EL RESULTADO SUMARLO AL PADRE

     
    */

}
