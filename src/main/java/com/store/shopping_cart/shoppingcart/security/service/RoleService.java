package com.store.shopping_cart.shoppingcart.security.service;

import org.springframework.stereotype.Service;

import com.store.shopping_cart.shoppingcart.security.models.Role;
import com.store.shopping_cart.shoppingcart.security.models.RoleName;

@Service
public interface RoleService {

    /**
     * Get role by name
     * @param roleName
     * @return
     */
    Role findRoleByName(RoleName roleName);
}
