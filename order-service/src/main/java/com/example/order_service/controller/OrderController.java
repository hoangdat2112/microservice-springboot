package com.example.order_service.controller;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CircuitBreaker(name="inventory" ,fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "order successfully";
    }
    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return "Opps ! Something went wrong ,please order after some time";
    }
  }
