package com.doziem.pos.model;

import com.doziem.pos.enums.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;

    private String name;
    private String description;
    private double sellingPrice;
    private String sku;
    private int quantity;

    private String mrp;

    private String brand;

    private Category category;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void onCreated(){
        createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    protected void onUpdated(){
        updatedAt = ZonedDateTime.now();
    }
}
