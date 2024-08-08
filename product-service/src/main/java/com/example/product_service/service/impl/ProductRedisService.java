package com.example.product_service.service.impl;

import com.example.product_service.dto.response.ProductResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRedisService implements IProductRedisService{
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper redisObjectMapper;
    @Value("${spring.data.redis.use-redis-cache}")
    private boolean useRedisCache;
    private String getKeyFrom(String keyword,

                       PageRequest pageRequest) {
        int pageNumber = pageRequest.getPageNumber();
        int pageSize = pageRequest.getPageSize();
        Sort sort = pageRequest.getSort();
        String sortDirection = sort.getOrderFor("id")
                .getDirection() == Sort.Direction.ASC ? "asc": "desc";
        String key = String.format("all_products:%s:%d:%d:%s",
                keyword,  pageNumber, pageSize, sortDirection);
        return key;
    }
    @Override
    public List<ProductResponse> getAllProducts(String keyword,

                                                PageRequest pageRequest) throws JsonProcessingException {

        if(useRedisCache == false) {
            return null;
        }
        String key = this.getKeyFrom(keyword, pageRequest);
        String json = (String) redisTemplate.opsForValue().get(key);
        List<ProductResponse> productResponses =
                json != null ?
                redisObjectMapper.readValue(json, new TypeReference<List<ProductResponse>>() {})
                : null;
        return productResponses;
    }
    @Override
    public void clear(){
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Override
    //save to Redis
    public void saveAllProducts(List<ProductResponse> productResponses,
                                String keyword,

                                PageRequest pageRequest) throws JsonProcessingException {
        String key = this.getKeyFrom(keyword, pageRequest);
        String json = redisObjectMapper.writeValueAsString(productResponses);
        redisTemplate.opsForValue().set(key, json);
    }
}
