package com.doziem.pos.service;

import com.doziem.pos.request.StoreRequest;
import com.doziem.pos.response.StoreResponse;
import com.doziem.pos.system.Result;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {

    StoreResponse createStore(StoreRequest request, String userId);
    StoreResponse updateStore(StoreRequest request, String storeId, String userId);
    StoreResponse getStoreById(String storeId);
    List<StoreResponse> getAllStores(Pageable pageable);

    void deleteStore(String storeId, String userId);
    List<StoreResponse> searchStores(String query, Pageable pageable);
    List<StoreResponse> getStoresByStatus(String status, Pageable pageable);
}
