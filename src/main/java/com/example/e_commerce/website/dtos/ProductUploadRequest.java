package com.example.e_commerce.website.dtos;

import com.example.e_commerce.website.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUploadRequest {

    @NotBlank(message = "product name required!")
    private String productName;

    @NotBlank (message = "product description required!")
    private String description;

    @Min(value = 1, message = "price must be at least 1")
    private BigDecimal price;

    @Min(value = 0, message = "quantity must be at leas 0 or more")
    private int quantity;

    @NotBlank(message = "category is required")
    private String category;

    @NotBlank(message = "ImageUrl is required")
    private String imageUrl;

    @NotNull(message = "seller_Id is required")
    private Long sellerId;
}
