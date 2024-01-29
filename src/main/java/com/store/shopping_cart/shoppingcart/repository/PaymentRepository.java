package com.store.shopping_cart.shoppingcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.models.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByIdOrder(Order idOrder);

    @Query( value = "select od from order_detail od where od.id_order = :idOrder", nativeQuery = true )
    Optional<Payment> findByIdOrder(@Param("idOrder") int idOrder);

}
