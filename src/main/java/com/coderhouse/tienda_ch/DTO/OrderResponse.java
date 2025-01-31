package com.coderhouse.tienda_ch.DTO;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private LocalDateTime fecha;
    private Double total;
    private UserResponse usuario;
    private List<OrderDetailResponse> detalles;
}
