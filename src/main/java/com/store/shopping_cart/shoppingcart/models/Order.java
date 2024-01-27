package com.store.shopping_cart.shoppingcart.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;
    private double totalAmount;
    private int totalItems;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToOne(mappedBy = "idOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment idPayment;
    private Date creatDate;
    private Date updateDate;

    @JsonIgnore
    @OneToMany(mappedBy = "idOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

}
