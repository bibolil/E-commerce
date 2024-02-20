package com.example.ecommerce.item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table
public class Item {
    @Id
    private long code;

    private String description;
    private Double price;
    private String picture;
    private Integer InStock;

    public Item() {
    }

    public Item(long code, String description, Double price, String picture, Integer inStock) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.picture = picture;
        InStock = inStock;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getInStock() {
        return InStock;
    }

    public void setInStock(Integer inStock) {
        InStock = inStock;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", InStock=" + InStock +
                '}';
    }
}

