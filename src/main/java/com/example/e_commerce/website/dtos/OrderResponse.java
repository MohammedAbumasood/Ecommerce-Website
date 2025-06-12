package com.example.e_commerce.website.dtos;

import com.example.e_commerce.website.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderResponse {


    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private OrderStatus status;
    private LocalDateTime orderTime;
}
