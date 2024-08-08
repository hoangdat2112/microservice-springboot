package com.example.product_service.service.impl;

import com.example.product_service.dto.ProductDTO;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws Exception;
    Product getProductById(long id) throws Exception;
    public Page<ProductResponse> getAllProducts(String keyword,
                                                 PageRequest pageRequest);
    Product updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
    List<Product> findProductsByIds(List<Long> productIds);
    }