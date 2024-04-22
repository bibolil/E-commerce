package com.example.ecommerce.item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
@Table
public class Item {
    @Id
    private long code;
    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private Double price;
    @Setter
    private String image;
    @Setter
    private Integer inventoryStatus;
    @Setter
    private long quantity;
    @Setter
    private int rating;
   public Item() {
    }

    public Item(long code, String name, String description, Double price, String image, Integer inventoryStatus, long quantity, int rating) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.inventoryStatus = inventoryStatus;
        this.quantity = quantity;
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

