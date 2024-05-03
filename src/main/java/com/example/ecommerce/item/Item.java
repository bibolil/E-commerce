package com.example.ecommerce.item;
import com.example.ecommerce.orderItem.OrderItem;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code")
public class Item {
    @Id
    @Getter
    private String code;
    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private String image;
    @Setter
    private Double price;
    @Setter
    private String category;
    @Setter
    private long quantity;
    @Setter
    private String inventoryStatus;
    @Setter
    private int rating;

    @OneToMany(mappedBy = "item")
    private Set<OrderItem> orderItems;
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

