package com.example.e_commerce.website.dtos;

import com.example.e_commerce.website.enums.OrderStatus;
import com.example.e_commerce.website.model.Product;
import com.example.e_commerce.website.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRequest {

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Product Id is required")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    private OrderStatus status;

    @NotBlank(message = "shippingAddress is required")
    private String shippingAddress;
}
