package com.example.tienda_ch.database;

import com.example.tienda_ch.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

