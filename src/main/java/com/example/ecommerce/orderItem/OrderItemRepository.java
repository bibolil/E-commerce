package com.example.ecommerce.orderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    @Query("SELECT o FROM OrderItem o WHERE o.order.id = :orderId AND o.item.code = :itemCode")
    Optional<OrderItem> findByOrderIdAndItemId(Long orderId, String itemCode);
}
