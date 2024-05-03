package com.example.ecommerce.orderItem;

import org.springframework.web.bind.annotation.PathVariable;

public class UpdateOrderPayload {
    public Long orderId;
    public String itemCode;
    public int quantity;
}
