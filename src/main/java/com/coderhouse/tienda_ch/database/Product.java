package com.coderhouse.tienda_ch.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description="Modelo de productos", title = "Modelo de producto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del producto",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nombre de del producto")
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Category categoria;

    @Column(nullable = false)
    private Integer stock;

}
