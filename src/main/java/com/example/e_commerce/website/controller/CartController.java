package com.example.e_commerce.website.controller;

import com.example.e_commerce.website.dtos.CartRequest;
import com.example.e_commerce.website.dtos.CartResponse;
import com.example.e_commerce.website.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/save")
    public String addToCart(@Valid @RequestBody CartRequest request){
        String response = cartService.addToCart(request);
        return response;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/getAll")
    public List<CartResponse> getAll(@RequestHeader("Authorization") String token) {
        return cartService.getCartList(token);
    }


    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/delete/{id}")
    public String deleteCartById(@PathVariable Long id){
        String response = cartService.deleteCartById(id);
        return response;
    }
}
