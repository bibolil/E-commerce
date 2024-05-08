package com.example.ecommerce.Order;

import com.example.ecommerce.orderItem.OrderItem;
import com.example.ecommerce.orderItem.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Setter
@Getter
public class OrderDTO {
    private Integer id;
    private String status;
    private String paymentMethod;
    private Date createdAt;
    private List<OrderItemDTO> orderItems;
    }


