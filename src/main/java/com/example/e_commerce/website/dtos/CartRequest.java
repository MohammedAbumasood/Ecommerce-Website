package com.example.e_commerce.website.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartRequest {

    @NotNull(message = "user id is required")
    private Long userId;

    @NotNull(message = "product id is required")
    private Long productId;

    @Min(value = 1, message = "quantity must be one")
    private int quantity;
}
