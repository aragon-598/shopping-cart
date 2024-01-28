package com.store.shopping_cart.shoppingcart.dto;

import java.util.Date;

import com.store.shopping_cart.shoppingcart.security.dto.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class OrderResponse {
    private Integer idOrder;
    private double totalAmount;
    private int totalItems;
    private String status;
    private UserResponse idUser;
    private Date creatDate;
    private Date updateDate;
}
