package com.example.e_commerce.website.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditProductRequest {

    @NotBlank(message = "ProductName is required!")
    private String productName;

    @NotBlank(message = "Description is required!")
    private String description;

    @Min(value = 1, message = "price must be at least 1")
    private int price;

    @Min(value = 0, message = "quantity must be at leas 0 or more")
    private int quantity;

    @NotBlank(message = "category is required")
    private String category;

    @NotBlank(message = "ImageUrl id required!")
    private String imageUrl;
}
