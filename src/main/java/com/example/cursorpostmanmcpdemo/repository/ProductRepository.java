package com.example.cursorpostmanmcpdemo.repository;

import com.example.cursorpostmanmcpdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
