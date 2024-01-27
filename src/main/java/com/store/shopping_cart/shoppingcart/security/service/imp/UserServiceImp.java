package com.store.shopping_cart.shoppingcart.security.service.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;
import com.store.shopping_cart.shoppingcart.security.models.User;
import com.store.shopping_cart.shoppingcart.security.repository.UserRepository;
import com.store.shopping_cart.shoppingcart.security.service.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

@Override
    public UserResponse getUserById(int userId) {
        UserResponse response = modelMapper.map(repository.findById(userId).orElse(null), UserResponse.class);
        return response;
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        UserResponse response = modelMapper.map(repository.findByUsername(username).orElse(null), UserResponse.class);
        return response;
    }
    
    @Override
    public UserResponse saveUser(UserRequest user) {

        if (user == null) {
            return null;
        }
        User newUser = modelMapper.map(user, User.class);

        String encodePassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodePassword);


        UserResponse response = modelMapper.map(repository.save(newUser), UserResponse.class);
        return response;
    }

    @Override
    public boolean deleteUserById(int userId) {
        User userDeleted = repository.findById(userId).orElse(null);

        if (userDeleted == null) {
            return false;
        }

        userDeleted.setActive(false);
        repository.save(userDeleted);

        return true;
    }

}
