package com.example.e_commerce.website.controller;

import com.example.e_commerce.website.dtos.EditProductRequest;
import com.example.e_commerce.website.dtos.ProductResponse;
import com.example.e_commerce.website.dtos.ProductUploadRequest;
import com.example.e_commerce.website.model.Product;
import com.example.e_commerce.website.model.User;
import com.example.e_commerce.website.service.ProductService;
import com.example.e_commerce.website.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/upload")
    public String addProduct(@Valid @RequestBody ProductUploadRequest request){
        String response = productService.addProduct(request);
        return response;
    }

    @GetMapping("/get/{id}")
    public ProductResponse findProductById(@PathVariable Long id){
        ProductResponse product = productService.findProductById(id);
        return product;
    }

    //@PreAuthorize("hasAnyRole('SELLER', 'CUSTOMER')")
    @GetMapping("/findAll")
    public List<ProductResponse> findAllPro(){
        List<ProductResponse> responses = productService.findAllProducts();
        return responses;
    }

    @PreAuthorize("hasRole('SELLER')")
    @PutMapping("/update/{id}")
    public String updateProductById(@Valid @RequestBody EditProductRequest request,@PathVariable Long id){
        String response = productService.updateById(request, id);
        return response;
    }

    @PreAuthorize("hasRole('SELLER')")
    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id){
        String response = productService.deleteProductById(id);
        return  response;
    }
}
