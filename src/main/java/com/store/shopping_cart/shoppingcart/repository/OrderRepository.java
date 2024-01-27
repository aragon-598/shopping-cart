package com.store.shopping_cart.shoppingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.security.models.User;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<List<Order>> findByIdUser(User idUser);
}
