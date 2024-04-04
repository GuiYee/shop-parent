package com.example.service;


import com.example.model.Order;

public interface OrderService {

    Order create(Long productId, Long userId);
}
