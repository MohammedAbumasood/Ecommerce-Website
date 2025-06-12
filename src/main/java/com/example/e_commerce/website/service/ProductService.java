package com.example.e_commerce.website.service;

import com.example.e_commerce.website.dtos.EditProductRequest;
import com.example.e_commerce.website.dtos.ProductResponse;
import com.example.e_commerce.website.dtos.ProductUploadRequest;
import com.example.e_commerce.website.mapper.ProductMapper;
import com.example.e_commerce.website.model.Product;
import com.example.e_commerce.website.model.User;
import com.example.e_commerce.website.repository.ProductRepository;
import com.example.e_commerce.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    @Autowired
    public ProductService (ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    public String addProduct(ProductUploadRequest request) {
        User seller = userRepository.findById(request.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found with id " + request.getSellerId()));
        Product product = productMapper.mapToEntity(request, seller);
        productRepository.save(product);
        return "Product added successfully";
    }

    public ProductResponse findProductById(Long id){
        Product product = productRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Product not found with id "+id));
        return productMapper.mapToResponse(product);
    }

    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::mapToResponse)
                .toList();
    }

    public String updateById(EditProductRequest request, Long id){
        Product product = productRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Product not found with id "+id));
        productMapper.mapToEditRequest(request, product);
        productRepository.save(product);
        return "Product updated successfully";
    }

    public String deleteProductById(Long id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product not found with id "+id);
        }

        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
}
