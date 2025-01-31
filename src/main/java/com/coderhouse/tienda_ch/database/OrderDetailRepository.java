package com.coderhouse.tienda_ch.database;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}