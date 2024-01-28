package com.store.shopping_cart.shoppingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.models.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    Optional<List<OrderDetail>> findByIdOrder(Order idOrder);
    
    @Query( value = "select * from order_detail od where od.id_order = :idOrder", nativeQuery = true )
    Optional<List<OrderDetail>> findByIdOrder(@Param("idOrder") int idOrder);
}
