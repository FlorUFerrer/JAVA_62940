package com.coderhouse.tienda_ch.DTO;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private Long id;
    private String productoNombre;
    private Integer cantidad;
    private Double subtotal;
}
