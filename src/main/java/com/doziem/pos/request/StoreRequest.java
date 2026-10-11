package com.doziem.pos.request;

import com.doziem.pos.enums.StoreStatus;
import com.doziem.pos.model.StoreContact;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreRequest {

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Store type is required")
    private String storeType;

    private StoreStatus status;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @NotNull(message = "Contact is required")
    private StoreContact contact;
}
