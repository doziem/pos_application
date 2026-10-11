package com.doziem.pos.response;

import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String productId;

    private String name;
    private String description;
    private double sellingPrice;
    private String sku;
    private int quantity;

    private String categoryId;
    private String categoryName;
    private String mrp;

    private String brand;

    private String image;

    private String storeId;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
