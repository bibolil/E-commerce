package com.example.ecommerce.Order;

import com.example.ecommerce.Order.OrderRepository;
import com.example.ecommerce.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder(Long id)
    {
        return  orderRepository.findById(id);
    }

    public Optional<Order> getUserOrders(Long id)
    {
        return  orderRepository.findUserOrders(id);
    }

    public void createOrder(Order order)
    {
        orderRepository.save((order));

    }
}
