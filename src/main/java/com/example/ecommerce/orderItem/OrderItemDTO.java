package com.example.ecommerce.orderItem;

import com.example.ecommerce.item.ItemDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDTO {
    private ItemDTO item;
    private Integer quantity;
}