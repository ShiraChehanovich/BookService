package com.example.bookservice.repository;

import com.example.bookservice.common.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
