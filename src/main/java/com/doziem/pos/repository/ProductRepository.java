package com.doziem.pos.repository;

import com.doziem.pos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByProductId(String productId);
    Optional<Product> findByStoreId(String storeId);

    Optional<Product> findByStoreIdAndProductId(String storeId, String productId);
}
