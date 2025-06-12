package com.example.e_commerce.website.controller;

import com.example.e_commerce.website.dtos.EditOrderRequest;
import com.example.e_commerce.website.dtos.OrderRequest;
import com.example.e_commerce.website.dtos.OrderResponse;
import com.example.e_commerce.website.model.Order;
import com.example.e_commerce.website.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public String saveOrder(@Valid @RequestBody OrderRequest request){
        String response = orderService.saveOrder(request);
        return response;
    }

    @GetMapping("/getAll")
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/get/{id}")
    public OrderResponse getOrderById(@PathVariable Long id){
        OrderResponse orderResponse = orderService.getOrderById(id);
        return orderResponse;
    }

    @PutMapping("/update/{id}")
    public String updateOrderById(@Valid @RequestBody EditOrderRequest request,@PathVariable Long id){
        String response = orderService.updateOrderById(request, id);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrderById(@PathVariable Long id){
        String response = orderService.deleteOrderById(id);
        return response;
    }
}
