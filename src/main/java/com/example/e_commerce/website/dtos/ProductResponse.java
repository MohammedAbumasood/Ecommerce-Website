package com.example.e_commerce.website.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {

    private Long id;
    private String productName;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String category;
    private String imageUrl;
    private LocalDateTime createdAt;
    private String sellerName;
}
