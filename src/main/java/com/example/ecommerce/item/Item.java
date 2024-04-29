package com.example.ecommerce.item;

import com.example.ecommerce.cart.Cart;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code")
public class Item {
    @Id
    private String code;

    private String name;

    private String description;

    private String image;

    private Double price;

    private String category;

    private long quantity;

    private String inventoryStatus;

    private int rating;

    @ManyToOne
    private Cart cart;




   public Item() {
    }

    public Item(String code, String name, String description, String image, Double price, String category,long quantity,String inventoryStatus,  int rating) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.inventoryStatus = inventoryStatus;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", inventoryStatus=" + inventoryStatus +
                ", quantity=" + quantity +
                ", rating=" + rating +
                '}';
    }



}

