package com.store.shopping_cart.shoppingcart.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.shopping_cart.shoppingcart.security.models.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Order  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;
    private double totalAmount;
    private int totalItems;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToOne(mappedBy = "idOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment idPayment;
    @CreationTimestamp
    private Date creatDate;
    @UpdateTimestamp
    private Date updateDate;
    
    @JoinColumn(name = "id_user", referencedColumnName = "userId", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User idUser;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrder", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

}
