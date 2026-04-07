package com.example.cursorpostmanmcpdemo.repository;

import com.example.cursorpostmanmcpdemo.entity.OrderItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @EntityGraph(attributePaths = {"order", "product"})
    @Override
    List<OrderItem> findAll();

    @EntityGraph(attributePaths = {"order", "product"})
    @Override
    Optional<OrderItem> findById(Long id);

    @EntityGraph(attributePaths = {"order", "product"})
    List<OrderItem> findByOrderId(Long orderId);
}
