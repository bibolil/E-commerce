package com.example.ecommerce.Order;
import com.example.ecommerce.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Optional<Order> findById(Long id);

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    Optional<Order> findUserOrders(Long userId);
    @Query("SELECT o FROM Order o where o.user.id = :userId AND o.status = :status ORDER BY o.createdAt DESC")
    Optional<Order> findFirstByUserAndStatusOrderByCreatedAtDesc(Long userId, OrderStatus status);



}
