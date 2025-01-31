package com.coderhouse.tienda_ch.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orden_detalle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo de Detalle de Orden", title = "Detalle de Orden")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del detalle de la orden", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    @Schema(description = "Orden asociada")
    private Order orden;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @Schema(description = "Producto asociado")
    private Product producto;

    @Column(nullable = false)
    @Schema(description = "Cantidad del producto en la orden")
    private Integer cantidad;

    @Column(nullable = false)
    @Schema(description = "Precio total del producto (cantidad * precio unitario)")
    private Double subtotal;
}
