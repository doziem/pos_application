package com.doziem.pos.mapper;

import com.doziem.pos.model.Store;
import com.doziem.pos.request.StoreRequest;
import com.doziem.pos.response.StoreResponse;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    public static StoreRequest toEntity(Store store) {
        if (store == null) {
            return null;
        }
        return StoreRequest.builder()
                .brand(store.getBrand())
                .description(store.getDescription())
                .storeType(store.getStoreType())
                .status(store.getStatus())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .contact(store.getContact())
                .build();
    }


    public static Store toRequest(StoreRequest request) {
        if (request == null) {
            return null;
        }
        return Store.builder()
                .brand(request.getBrand())
                .description(request.getDescription())
                .storeType(request.getStoreType())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .contact(request.getContact())
                .build();
    }

    public static StoreResponse toResponse(Store store) {
        if (store == null) {
            return null;
        }
        return StoreResponse.builder()
                .storeId(store.getStoreId())
                .brand(store.getBrand())
                .storeAdmin(store.getStoreAdmin() != null ? UserMapper.toDto(store.getStoreAdmin()) : null)
                .description(store.getDescription())
                .storeType(store.getStoreType())
                .status(store.getStatus())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .contact(store.getContact())
                .build();
    }
}
