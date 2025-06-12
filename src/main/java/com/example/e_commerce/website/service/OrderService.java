package com.example.e_commerce.website.service;

import com.example.e_commerce.website.dtos.EditOrderRequest;
import com.example.e_commerce.website.dtos.OrderRequest;
import com.example.e_commerce.website.dtos.OrderResponse;
import com.example.e_commerce.website.mapper.OrderMapper;
import com.example.e_commerce.website.model.Order;
import com.example.e_commerce.website.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public String saveOrder(OrderRequest orderRequest){
        Order order = orderMapper.mapToEntity(orderRequest);
        orderRepository.save(order);
        return "Order is saved successfully";
    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::mapToResponse)
                .toList();
    }

    public OrderResponse getOrderById(Long id){
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No order found with id "+id));
        return orderMapper.mapToResponse(order);
    }

    public String updateOrderById(EditOrderRequest request, Long id){
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new RuntimeException("order not found with id "+id));
        orderMapper.mapToEditRequest(request, order);
        orderRepository.save(order);
        return "Order saved successfully";
    }

    public String deleteOrderById(Long id){
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Order Not found with id "+id));
        orderRepository.deleteById(id);
        return "Order cancelled Successfully";
    }
}