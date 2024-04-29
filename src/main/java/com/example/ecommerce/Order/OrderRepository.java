package com.example.ecommerce.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);

    @Query("SELECT o FROM Order o WHERE o.cart.user.id= ?1")
    Optional<Order> findUserOrders(Long id);
}
