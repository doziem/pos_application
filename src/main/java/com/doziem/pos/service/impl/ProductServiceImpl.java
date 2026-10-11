package com.doziem.pos.service.impl;

import com.doziem.pos.repository.ProductRepository;
import com.doziem.pos.request.ProductRequest;
import com.doziem.pos.response.ProductResponse;
import com.doziem.pos.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        return null;
    }

    @Override
    public ProductResponse getProductById(String productId) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(String productId, ProductRequest request) {
        return null;
    }

    @Override
    public void deleteProduct(String productId) {

    }

    @Override
    public List<ProductResponse> getAllProducts(Pageable pageable) {
        return List.of();
    }

    @Override
    public List<ProductResponse> getProductsByStoreId(String storeId, Pageable pageable) {
        return List.of();
    }

    @Override
    public List<ProductResponse> searchProducts(String query, Pageable pageable) {
        return List.of();
    }
}
