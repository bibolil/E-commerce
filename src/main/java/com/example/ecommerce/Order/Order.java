package com.example.ecommerce.Order;

import com.example.ecommerce.cart.Cart;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders") // You might want to specify the table name explicitly
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PaymentMethods paymentMethod;

    @OneToOne
    @JoinColumn(name = "cart")
    private Cart cart;

    public Order() {

    }

}
