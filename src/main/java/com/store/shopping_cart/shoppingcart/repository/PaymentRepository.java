package com.store.shopping_cart.shoppingcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.models.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    /**
     * Get payment by order
     * @param idOrder
     * @return
     */
    Optional<Payment> findByIdOrder(Order idOrder);

}
