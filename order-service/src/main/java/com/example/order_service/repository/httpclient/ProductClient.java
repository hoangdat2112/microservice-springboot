package com.example.order_service.repository.httpclient;

import com.example.order_service.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/api/product/by-ids")
    List<ProductResponse> getProductsByIds(@RequestParam List<Long> ids);
}

