package com.example.cursorpostmanmcpdemo.repository;

import com.example.cursorpostmanmcpdemo.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"user", "items", "items.product"})
    @Override
    List<Order> findAll();

    @EntityGraph(attributePaths = {"user", "items", "items.product"})
    @Override
    Optional<Order> findById(Long id);

    @EntityGraph(attributePaths = {"user", "items", "items.product"})
    List<Order> findByUserId(Long userId);
}
