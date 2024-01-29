package com.store.shopping_cart.shoppingcart.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.shopping_cart.shoppingcart.security.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Get user by username
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);
}