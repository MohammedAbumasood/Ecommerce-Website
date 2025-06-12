package com.example.e_commerce.website.mapper;

import com.example.e_commerce.website.dtos.EditProductRequest;
import com.example.e_commerce.website.dtos.ProductResponse;
import com.example.e_commerce.website.dtos.ProductUploadRequest;
import com.example.e_commerce.website.model.Product;
import com.example.e_commerce.website.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {

    public Product mapToEntity(ProductUploadRequest request, User seller){
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        product.setSeller(seller);
        return product;
    }

    public ProductResponse mapToResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setProductName(product.getProductName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setCategory(product.getCategory());
        response.setImageUrl(product.getImageUrl());
        response.setCreatedAt(product.getCreatedAt());
        response.setSellerName(product.getSeller().getUserName());
        return response;
    }

    public void mapToEditRequest(EditProductRequest request, Product product){
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(BigDecimal.valueOf(request.getPrice()));
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(request.getCategory());
    }

}
