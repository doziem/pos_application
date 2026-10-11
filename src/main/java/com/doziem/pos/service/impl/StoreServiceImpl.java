package com.doziem.pos.service.impl;

import com.doziem.pos.enums.StoreStatus;
import com.doziem.pos.enums.UserRole;
import com.doziem.pos.exception.ResourceNotFoundException;
import com.doziem.pos.mapper.StoreMapper;
import com.doziem.pos.model.Store;
import com.doziem.pos.model.User;
import com.doziem.pos.repository.StoreRepository;
import com.doziem.pos.repository.UserRepository;
import com.doziem.pos.request.StoreRequest;
import com.doziem.pos.response.StoreResponse;
import com.doziem.pos.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    private final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

    @Override
    public StoreResponse createStore(StoreRequest request, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getRole() != null && !user.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new ResourceNotFoundException("User is not authorized to create a store");
        }

        Store store = StoreMapper.toRequest(request);
        store.setStoreAdmin(user);

        return StoreMapper.toResponse(storeRepository.save(store));
    }

    @Override
    public StoreResponse updateStore(StoreRequest request, String storeId, String userId) {

        Store store = storeRepository.findByStoreId(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        if (!store.getStoreAdmin().getUserId().equals(userId)) {
            throw new ResourceNotFoundException("Unauthorized to update store details");
        }
        store.setBrand(request.getBrand() != null ? request.getBrand() : store.getBrand());
        store.setDescription(request.getDescription() != null ? request.getDescription() : store.getDescription());
        store.setStoreType(request.getStoreType() != null ? request.getStoreType() : store.getStoreType());
        store.setStatus(request.getStatus() != null ? request.getStatus() : store.getStatus());
        store.setContact(request.getContact() != null ? request.getContact() : store.getContact());

        return StoreMapper.toResponse(storeRepository.save(store));

    }

    @Override
    public StoreResponse getStoreById(String storeId) {

        return storeRepository.findByStoreId(storeId)
                .map(StoreMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
    }

    @Override
    public List<StoreResponse> getAllStores(Pageable pageable) {
        return storeRepository.findAll(pageable)
                .map(StoreMapper::toResponse)
                .getContent();
    }

    @Override
    public void deleteStore(String storeId, String userId) {

        Store store = storeRepository.findByStoreId(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        if (!store.getStoreAdmin().getUserId().equals(userId) && store.getStoreAdmin().getRole() != UserRole.ROLE_ADMIN) {
            logger.warn("Unauthorized attempt to delete store with ID: {}", storeId);
            throw new ResourceNotFoundException("Unauthorized to delete store");
        }

        storeRepository.delete(store);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreResponse> searchStores(String query, Pageable pageable) {
        if(query == null || query.isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be null or empty");
        }

        return storeRepository.searchStores(query, pageable)
                .stream()
                .map(StoreMapper::toResponse)
                .toList();
    }

    @Override
    public List<StoreResponse> getStoresByStatus(String status, Pageable pageable) {
        if(status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        pageable = Pageable.ofSize(pageable.getPageSize()).withPage(pageable.getPageNumber());

        StoreStatus storeStatus;
        try {
            storeStatus = StoreStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid store status: " + status);
        }

        return storeRepository.findByStatus(storeStatus, pageable)
                .stream()
                .map(StoreMapper::toResponse)
                .toList();
    }

}
