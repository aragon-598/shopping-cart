package com.store.shopping_cart.shoppingcart.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrderDetail;
    private int quantity;
    private double amount;
    private int idProduct;
    private double productPrice;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", referencedColumnName = "idOrder", nullable = false)
    private Order idOrder;

}
