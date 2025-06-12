package com.example.e_commerce.website.controller;

import com.example.e_commerce.website.dtos.CartRequest;
import com.example.e_commerce.website.dtos.CartResponse;
import com.example.e_commerce.website.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/save")
    public String addToCart(@Valid @RequestBody CartRequest request){
        String response = cartService.addToCart(request);
        return response;
    }

    @GetMapping("/getAll")
    public List<CartResponse> getCartList(){
        return cartService.getCartList();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCartById(@PathVariable Long id){
        String response = cartService.deleteCartById(id);
        return response;
    }
}
