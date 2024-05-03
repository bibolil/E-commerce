package com.example.ecommerce.orderItem;
import com.example.ecommerce.Order.Order;
import com.example.ecommerce.Order.OrderRepository;
import com.example.ecommerce.item.Item;
import com.example.ecommerce.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping(path = "orderItem")
public class OrderItemController {
    private final OrderItemRepository orderItemRepository;
    private  final ItemRepository itemRepository;
    private  final OrderRepository orderRepository;

    @Autowired
    public OrderItemController(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }
    @PostMapping(path="updateOrder")
    public void updateOrder(@RequestBody UpdateOrderPayload updateOrderPayload)
    {
        Optional<OrderItem> orderItem = orderItemRepository.findByOrderIdAndItemId(updateOrderPayload.orderId, updateOrderPayload.itemCode);
        if(orderItem.isEmpty()){
            Order order = orderRepository.findById(updateOrderPayload.orderId).orElseThrow(() -> new RuntimeException("Order not found"));
            Item item = itemRepository.findByCode(updateOrderPayload.itemCode).orElseThrow(() -> new RuntimeException("Item not found"));
            orderItemRepository.save(new OrderItem(order, item, updateOrderPayload.quantity));
            return;
        }
        OrderItem _orderItem = orderItem.get();
        _orderItem.setQuantity(updateOrderPayload.quantity);
        orderItemRepository.save(_orderItem);
    }
}
