package com.store.shopping_cart.shoppingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.shopping_cart.shoppingcart.models.Order;
import com.store.shopping_cart.shoppingcart.security.models.User;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<List<Order>> findByIdUser(User idUser);
    
    @Query( value = "select o from order o where o.id_user = :idUser", nativeQuery = true )
    Optional<List<Order>> findByIdUser(@Param("idUser") int idUser);
}
