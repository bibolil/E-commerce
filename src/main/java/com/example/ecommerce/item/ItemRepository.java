package com.example.ecommerce.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Optional<Item> findByCode(String code);

    void deleteByCode(String code);
}
