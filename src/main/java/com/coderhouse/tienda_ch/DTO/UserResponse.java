package com.coderhouse.tienda_ch.DTO;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String createdAt;
}
