package com.example.order_service.repository.httpclient;

import com.example.order_service.dto.response.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "inventory-service")

public interface InventoryClient {

    @GetMapping("/api/inventory")
    List<InventoryResponse> isInStock(@RequestParam List<String> skuCode);


}
