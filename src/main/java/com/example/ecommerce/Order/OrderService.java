package com.example.ecommerce.Order;

import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }
    public void validateOrder(Long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()) return ;
        Order order = optionalOrder.get();
        order.setStatus(OrderStatus.passed);
        orderRepository.save(order);
    }
    public Order getLatestPendingOrder(Long userId) {
        Optional<Order> latestPendingOrder = orderRepository.findFirstByUserAndStatusOrderByCreatedAtDesc(userId, OrderStatus.pending);
        if (latestPendingOrder.isPresent())
            return latestPendingOrder.get();

        Order newOrder = new Order();
        newOrder.setStatus(OrderStatus.pending);
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            return null;
        newOrder.setUser(user.get());
        return orderRepository.save(newOrder);
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
        orderRepository.save((order));
    }
}
