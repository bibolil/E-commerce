package com.example.ecommerce.Order;

import com.example.ecommerce.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping(path = "order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAnyAuthority('admin:read','user:read')")
    @GetMapping(path="getOrders")
    public List<Order> getOrders() {
        System.out.println("this is from getting orders ");
        return this.orderService.getAllOrders();
    }

    @PreAuthorize("hasAnyAuthority('admin:create','user:create')")
    @PostMapping(path="createOrder")
    public void createOrder(@RequestBody Order order)
    {
        this.orderService.createOrder(order);
    }

}
