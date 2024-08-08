package com.example.order_service.controller;

import com.example.order_service.dto.OrderDTO;
import com.example.order_service.dto.response.OrderResponse;
import com.example.order_service.model.Order;
import com.example.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String placeOrder(@RequestBody OrderDTO orderDTO){
        orderService.placeOrder(orderDTO);
        return "order successfully";
    }
//    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
//        return "Opps ! Something went wrong ,please order after some time";
//    }
//@GetMapping("/{id}")
//public ResponseEntity<?> getOrder(@Valid @PathVariable("id") Long orderId) {
//    try {
//        Order existingOrder = orderService.getOrder(orderId);
//        OrderResponse orderResponse = OrderResponse.fromOrder(existingOrder);
//        return ResponseEntity.ok(orderResponse);
//    } catch (Exception e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
//}
  }
