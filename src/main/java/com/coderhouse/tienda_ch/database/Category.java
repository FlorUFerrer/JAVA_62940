package com.coderhouse.tienda_ch.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description="Modelo de Categorias", title = "Modelo de categoria")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la categoría", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nombre de la categoría")
    private String nombre;
}
