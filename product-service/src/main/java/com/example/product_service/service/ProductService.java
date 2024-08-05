package com.example.product_service.service;

import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ProductService {
    void createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProduct();

}
