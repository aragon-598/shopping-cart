package com.store.shopping_cart.shoppingcart.security.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.security.models.Role;
import com.store.shopping_cart.shoppingcart.security.models.RoleName;
import com.store.shopping_cart.shoppingcart.security.repository.RoleRepository;
import com.store.shopping_cart.shoppingcart.security.service.RoleService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleRepository repository;

    @Override
    public Role findRoleByName(RoleName roleName) {
        return repository.findByRoleName(roleName).orElse(null);
    }
    

}
