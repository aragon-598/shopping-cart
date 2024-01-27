package com.store.shopping_cart.shoppingcart.security.service.imp;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.shopping_cart.shoppingcart.security.configuration.JwtProvider;
import com.store.shopping_cart.shoppingcart.security.dto.LoginRequest;
import com.store.shopping_cart.shoppingcart.security.dto.LoginResponse;
import com.store.shopping_cart.shoppingcart.security.dto.UserRequest;
import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;
import com.store.shopping_cart.shoppingcart.security.models.Role;
import com.store.shopping_cart.shoppingcart.security.models.RoleName;
import com.store.shopping_cart.shoppingcart.security.models.User;
import com.store.shopping_cart.shoppingcart.security.repository.UserRepository;
import com.store.shopping_cart.shoppingcart.security.service.AuthService;
import com.store.shopping_cart.shoppingcart.security.service.RoleService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    ModelMapper modelMapper;

     @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

         Authentication authentication=
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        User user = userRepository.findByUsername(username).orElse(null);

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);


        return new LoginResponse(userResponse, jwt);

        
    }

    @Override
    public UserResponse register(UserRequest user) {
        
        /*
         * Verificar si el user es null para terminar el request
         */
        if (user == null) {
            return new UserResponse(null, null, null, false, null);
        }
        
        //mapear el userRequest a un user
        User newUser = modelMapper.map(user, User.class);
        
        //Setear los roles de string - Role || codificar password
        Set<Role> roles = setRoles(user.getRoles());
        String encodePassword = passwordEncoder.encode(newUser.getPassword());

        newUser.setPassword(encodePassword);
        newUser.setRoles(roles);


        UserResponse response = modelMapper.map(userRepository.save(newUser), UserResponse.class);
        return response;
    }
    
    @Override
    public Set<Role> setRoles(Set<String> roleList){
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findRoleByName(RoleName.USER_ROLE));
        
        if (roleList.contains("admin")) {
            roles.add(roleService.findRoleByName(RoleName.ADMIN_ROLE));
        }

        return roles;
    }
    

}
