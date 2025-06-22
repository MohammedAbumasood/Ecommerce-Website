package com.example.e_commerce.website.service;

import com.example.e_commerce.website.dtos.CartRequest;
import com.example.e_commerce.website.dtos.CartResponse;
import com.example.e_commerce.website.mapper.CartMapper;
import com.example.e_commerce.website.model.Cart;
import com.example.e_commerce.website.model.User;
import com.example.e_commerce.website.repository.CartRepository;
import com.example.e_commerce.website.repository.UserRepository;
import com.example.e_commerce.website.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;
    private final JwtUtil jwtUtil;


    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper, UserRepository userRepository, JwtUtil jwtUtil){
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String addToCart(CartRequest request){
        Cart cart = cartMapper.mapToEntity(request);
        cartRepository.save(cart);
        return "Product added to cart successfully";
    }

    public List<CartResponse> getCartList(String token) {
        String actualToken = token.substring(7);
        String email = jwtUtil.extractUsername(actualToken);

        User user = userRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Cart> carts = cartRepository.findByUserId(user.getId());

        return carts.stream()
                .map(cartMapper::mapToResponse)
                .toList();
    }

    public String deleteCartById(Long id){
        Cart cart = cartRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Product not found in this cart id "+id));
        cartRepository.deleteById(id);
        return "Product Removed from the cart successfully";
    }
}
