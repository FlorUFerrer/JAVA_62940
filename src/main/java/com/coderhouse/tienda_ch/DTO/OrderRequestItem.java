package com.coderhouse.tienda_ch.DTO;

import lombok.Data;

@Data
public class OrderRequestItem {
    private Long productoId;
    private Integer cantidad;
}
