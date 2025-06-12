package com.example.e_commerce.website.dtos;

import com.example.e_commerce.website.enums.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EditOrderRequest {

    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private String shippingAddress;

    private OrderStatus status;

}
