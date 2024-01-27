package com.store.shopping_cart.shoppingcart.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPayment;
    @OneToOne
    @JoinColumn(name = "id_order", unique = true)
    private Order idOrder;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
