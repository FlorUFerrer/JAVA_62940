package com.coderhouse.tienda_ch.controllers;

import com.coderhouse.tienda_ch.DTO.*;
import com.coderhouse.tienda_ch.database.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orden")
@Tag(name="Ordenes", description = "Endpoint para ordenes")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        try {
            // Validar que el request no sea nulo y tenga items
            if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            // Obtener el usuario
            User usuario = userRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

            // Crear la orden inicial (sin detalles todavía)
            Order order = new Order();
            order.setUsuario(usuario);
            order.setFecha(LocalDateTime.now());
            order.setTotal(0.0);
            order.setStatus(OrderStatus.INACTIVA);

            // Validar stock de todos los productos antes de procesar
            for (OrderRequestItem item : request.getItems()) {
                Product producto = productRepository.findById(item.getProductoId())
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

                if (producto.getStock() < item.getCantidad()) {
                    throw new IllegalArgumentException("Stock insuficiente para el producto: "
                            + producto.getNombre() + ". Disponible: "
                            + producto.getStock() + ", Solicitado: "
                            + item.getCantidad());
                }
            }

            // Procesar los detalles y reducir el stock
            List<OrderDetail> detalles = request.getItems().stream().map(item -> {
                Product producto = productRepository.findById(item.getProductoId()).get();

                // Reducir stock (ya validado previamente)
                producto.setStock(producto.getStock() - item.getCantidad());
                productRepository.save(producto);

                // Crear el detalle de la orden
                OrderDetail detalle = new OrderDetail();
                detalle.setOrden(order);
                detalle.setProducto(producto);
                detalle.setCantidad(item.getCantidad());
                detalle.setSubtotal(producto.getPrecio() * item.getCantidad());

                return detalle;
            }).collect(Collectors.toList());

            // Calcular el total de la orden
            double total = detalles.stream()
                    .mapToDouble(OrderDetail::getSubtotal)
                    .sum();

            order.setTotal(total);
            order.setDetalles(detalles);

            Order savedOrder = orderRepository.save(order);

            OrderResponse response = mapToOrderResponse(savedOrder);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private OrderResponse mapToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setFecha(order.getFecha());
        response.setTotal(order.getTotal());

        // Mapear usuario
        UserResponse userResponse = new UserResponse();
        userResponse.setId(order.getUsuario().getId());
        userResponse.setUsername(order.getUsuario().getUsername());
        userResponse.setEmail(order.getUsuario().getEmail());
        userResponse.setCreatedAt(order.getUsuario().getCreatedAt().toString());
        response.setUsuario(userResponse);

        // Mapear detalles
        List<OrderDetailResponse> detailResponses = order.getDetalles().stream().map(detalle -> {
            OrderDetailResponse detailResponse = new OrderDetailResponse();
            detailResponse.setId(detalle.getId());
            detailResponse.setProductoNombre(detalle.getProducto().getNombre());
            detailResponse.setCantidad(detalle.getCantidad());
            detailResponse.setSubtotal(detalle.getSubtotal());
            return detailResponse;
        }).collect(Collectors.toList());
        response.setDetalles(detailResponses);

        return response;
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelarOrden(@PathVariable Long id) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));

            if (order.getStatus() == OrderStatus.ENTREGADA) {
                return ResponseEntity.badRequest().body("La orden ya fue entregada y no puede cancelarse.");
            }

            if (order.getStatus() == OrderStatus.CANCELADA || order.getStatus() == OrderStatus.REVERTIDA) {
                return ResponseEntity.badRequest().body("La orden ya está cancelada o revertida.");
            }

            // Actualizar el estado de la orden a CANCELADA
            order.setStatus(OrderStatus.CANCELADA);
            orderRepository.save(order);

            // Reverso técnico (si aplica)
            return ResponseEntity.ok("La orden ha sido cancelada exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    @PutMapping("/{id}/entregar")
    public ResponseEntity<String> entregarOrden(@PathVariable Long id) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));

            if (order.getStatus() == OrderStatus.ENTREGADA) {
                return ResponseEntity.badRequest().body("La orden ya está marcada como entregada.");
            }
            order.setStatus(OrderStatus.ENTREGADA);
            orderRepository.save(order);

            return ResponseEntity.ok("La orden ha sido marcada como entregada.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    @PutMapping("/{id}/revertir")
    public ResponseEntity<String> revertirOrden(@PathVariable Long id) {
        try {
            // Buscar la orden por ID
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));

            // Validar el estado actual de la orden
            if (order.getStatus() == OrderStatus.REVERTIDA || order.getStatus() == OrderStatus.CANCELADA) {
                return ResponseEntity.badRequest().body("La orden ya está en estado REVERTIDA o CANCELADA.");
            }

            // Revertir el stock de los productos
            for (OrderDetail detalle : order.getDetalles()) {
                Product producto = detalle.getProducto();
                producto.setStock(producto.getStock() + detalle.getCantidad());
                productRepository.save(producto);
            }

            // Actualizar el estado de la orden
            order.setStatus(OrderStatus.REVERTIDA);
            orderRepository.save(order);

            return ResponseEntity.ok("La orden ha sido revertida exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

}