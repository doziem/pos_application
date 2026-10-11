package com.doziem.pos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;
    @NotBlank(message = "Product description is required")
    private String description;
    @NotBlank(message = "Selling price is required")
    private double sellingPrice;
    @NotBlank(message = "SKU is required")
    private String sku;
    @NotBlank(message = "Quantity is required")
    private int quantity;

    @NotBlank(message = "Category ID is required")
    private String categoryId;

    @NotBlank(message = "MRP is required")
    private String mrp;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Image is required")
    private String image;

    @NotBlank(message = "Store ID is required")
    private String  storeId;

}
