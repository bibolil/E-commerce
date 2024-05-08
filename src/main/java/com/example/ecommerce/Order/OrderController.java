package com.example.ecommerce.Order;

import com.example.ecommerce.item.Item;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping(path = "order")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    //@PreAuthorize("hasAnyAuthority('admin:read','user:read')")
    @GetMapping(path="getOrders")
        public List<OrderDTO> getOrders() {
        System.out.println("this is from getting orders ");
        return this.orderService.getAllOrders().stream().map(order -> modelMapper.map(order,OrderDTO.class)).collect(Collectors.toList());
    }

    //@PreAuthorize("hasAnyAuthority('admin:read','user:read')")
    @GetMapping(path="getLatestPendingOrder/{userId}")
    public Order getLatestPendingOrder(@PathVariable ("userId") Long userId) {
        return this.orderService.getLatestPendingOrder(userId);
    }
    @GetMapping(path="validateOrder/{orderId}")
    public void validateOrder(@PathVariable ("orderId") Long orderId) {
        this.orderService.validateOrder(orderId);
    }

    @PreAuthorize("hasAnyAuthority('admin:create','user:create')")
    @PostMapping(path="createOrder")
    public void createOrder(@RequestBody Order order)
    {
        this.orderService.createOrder(order);
    }


}
