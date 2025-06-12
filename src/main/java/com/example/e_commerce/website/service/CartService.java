package com.example.e_commerce.website.service;

import com.example.e_commerce.website.dtos.CartRequest;
import com.example.e_commerce.website.dtos.CartResponse;
import com.example.e_commerce.website.mapper.CartMapper;
import com.example.e_commerce.website.model.Cart;
import com.example.e_commerce.website.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper){
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public String addToCart(CartRequest request){
        Cart cart = cartMapper.mapToEntity(request);
        cartRepository.save(cart);
        return "Product added to cart successfully";
    }

    public List<CartResponse> getCartList(){
        List<Cart> carts = cartRepository.findAll();
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
