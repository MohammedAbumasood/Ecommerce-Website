package com.example.e_commerce.website.mapper;

import com.example.e_commerce.website.dtos.EditOrderRequest;
import com.example.e_commerce.website.dtos.OrderRequest;
import com.example.e_commerce.website.dtos.OrderResponse;
import com.example.e_commerce.website.model.Order;
import com.example.e_commerce.website.model.Product;
import com.example.e_commerce.website.model.User;
import com.example.e_commerce.website.repository.ProductRepository;
import com.example.e_commerce.website.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMapper {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderMapper(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Order mapToEntity(OrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getProductId()));

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(request.getQuantity());
        order.setStatus(request.getStatus());
        order.setShippingAddress(request.getShippingAddress());
        order.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));


        return order;
    }

    public OrderResponse mapToResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setProductId(order.getProduct().getId());
        response.setProductName(order.getProduct().getProductName());
        response.setQuantity(order.getQuantity());
        response.setTotalAmount(order.getTotalAmount());
        response.setShippingAddress(order.getShippingAddress());
        response.setStatus(order.getStatus());
        response.setOrderTime(order.getOrderTime());
        return response;
    }

    public void mapToEditRequest(EditOrderRequest request, Order order) {
        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + request.getProductId()));
            order.setProduct(product);
            order.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        }
        if (request.getQuantity() != null && request.getQuantity() > 0) {
            order.setQuantity(request.getQuantity());
        }
        if (request.getShippingAddress() != null) {
            order.setShippingAddress(request.getShippingAddress());
        }
        if (request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }
    }

}
