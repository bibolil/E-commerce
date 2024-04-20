package com.example.ecommerce.cart;


import com.example.ecommerce.item.Item;
import com.example.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Getter
@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<Item> items = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("cart")
    private User user; // If user authentication is used

    public Cart() {
    }

    public Cart(User user, List<Item> items) {
        this.user = user;
        this.items = items;
    }
    public Cart(User user) {
        this.user = user;
    }

    // Method to add an item to the cart
    public void addItem(Item item) {
        items.add(item);
        item.setCart(this); // Set the cart of the item to this cart
    }

}
