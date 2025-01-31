
package com.coderhouse.tienda_ch.DTO;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private Long usuarioId;
    private List<OrderRequestItem> items;
}
