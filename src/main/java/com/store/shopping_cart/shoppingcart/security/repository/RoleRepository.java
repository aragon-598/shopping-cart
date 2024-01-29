package com.store.shopping_cart.shoppingcart.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.shopping_cart.shoppingcart.security.models.Role;
import com.store.shopping_cart.shoppingcart.security.models.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    /**
     * Get rol by RoleName enum
     * @param roleName
     * @return
     */
    Optional<Role> findByRoleName(RoleName roleName);
}
