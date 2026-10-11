package com.doziem.pos.mapper;

import com.doziem.pos.model.Product;
import com.doziem.pos.request.ProductRequest;
import com.doziem.pos.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

//    map to request
    public static Product mapToProduct(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .sellingPrice(request.getSellingPrice())
                .sku(request.getSku())
                .quantity(request.getQuantity())
                .mrp(request.getMrp())
                .brand(request.getBrand())
                .image(request.getImage())
                .build();
    }


//    map to response
  public static ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .sellingPrice(product.getSellingPrice())
                .sku(product.getSku())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getCategoryId())
                .categoryName(product.getCategory().getName())
                .mrp(product.getMrp())
                .brand(product.getBrand())
                .image(product.getImage())
                .storeId(product.getStore().getStoreId())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
