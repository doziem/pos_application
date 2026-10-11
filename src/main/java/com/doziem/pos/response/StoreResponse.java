package com.doziem.pos.response;

import com.doziem.pos.enums.StoreStatus;
import com.doziem.pos.model.StoreContact;
import com.doziem.pos.request.UserRequest;
import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse {
    private String storeId;
    private String brand;

    private UserRequest storeAdmin;

    private String description;

    private String storeType;

    private StoreStatus status;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    private StoreContact contact;
}
