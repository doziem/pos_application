package com.doziem.pos.service;

import com.doziem.pos.request.ProductRequest;
import com.doziem.pos.response.ProductResponse;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(String productId);

    ProductResponse updateProduct(String productId, ProductRequest request);

    void deleteProduct(String productId);

    List<ProductResponse> getAllProducts(Pageable pageable);

    List<ProductResponse> getProductsByStoreId(String storeId, Pageable pageable);

    List<ProductResponse> searchProducts(String query, Pageable pageable);

}
