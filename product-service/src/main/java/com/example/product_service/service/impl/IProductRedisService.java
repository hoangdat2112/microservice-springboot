package com.example.product_service.service.impl;

import com.example.product_service.dto.response.ProductResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductRedisService {
    //Clear cached data in Redis
    void clear();//clear cache
    List<ProductResponse> getAllProducts(
            String keyword,
            PageRequest pageRequest) throws JsonProcessingException;
    void saveAllProducts(List<ProductResponse> productResponses,
                                String keyword,
                                PageRequest pageRequest) throws JsonProcessingException;

}