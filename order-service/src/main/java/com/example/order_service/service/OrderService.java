package com.example.order_service.service;

import com.example.order_service.dto.OrderDTO;
import com.example.order_service.model.Order;

public interface OrderService {
    void placeOrder(OrderDTO orderRequest);
}
