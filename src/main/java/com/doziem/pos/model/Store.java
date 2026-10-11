package com.doziem.pos.model;

import com.doziem.pos.enums.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String storeId;

    private String brand;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User storeAdmin;

    private String description;

    private String storeType;

    private StoreStatus status;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    private StoreContact contact = new StoreContact();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    @PrePersist
    protected void onCreated(){
        createdAt = ZonedDateTime.now();
        status = StoreStatus.PENDING;
    }

    @PreUpdate
    protected void onUpdated(){
        updatedAt = ZonedDateTime.now();
    }
}
