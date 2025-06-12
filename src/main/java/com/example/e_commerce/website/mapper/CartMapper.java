package com.example.e_commerce.website.mapper;

import com.example.e_commerce.website.dtos.CartRequest;
import com.example.e_commerce.website.dtos.CartResponse;
import com.example.e_commerce.website.model.Cart;
import com.example.e_commerce.website.model.Product;
import com.example.e_commerce.website.model.User;
import com.example.e_commerce.website.repository.ProductRepository;
import com.example.e_commerce.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart mapToEntity(CartRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getProductId()));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(request.getQuantity());

        BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));
        cart.setTotalPrice(total);

        return cart;
    }

    public CartResponse mapToResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        response.setUserId(cart.getUser().getId());
        response.setProductId(cart.getProduct().getId());
        response.setProductName(cart.getProduct().getProductName());
        response.setQuantity(cart.getQuantity());
        response.setTotalPrice(cart.getTotalPrice());
        response.setAddedTime(cart.getAddedTime());

        return response;
    }
}
