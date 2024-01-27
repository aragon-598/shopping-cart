package com.store.shopping_cart.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.shopping_cart.shoppingcart.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}