package com.example.ecommerce.Order;

import com.example.ecommerce.orderItem.OrderItem;
import com.example.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Getter
@Setter
@Entity
@Table(name = "orders") // You might want to specify the table name explicitly
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.pending;

    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod=PaymentMethods.OnDelivary;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Order() {
        // Default constructor
    }
}
