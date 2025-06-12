package com.example.e_commerce.website.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CartResponse {

        private Long id;
        private Long userId;
        private Long productId;
        private String productName;
        private int quantity;
        private BigDecimal totalPrice;
        private LocalDateTime addedTime;
}
