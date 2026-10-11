package com.doziem.pos.controller;

import com.doziem.pos.request.StoreRequest;
import com.doziem.pos.service.StoreService;
import com.doziem.pos.service.impl.StoreServiceImpl;
import com.doziem.pos.system.Result;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    private final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @PostMapping("/create/{userId}")
    public ResponseEntity<Result> createStore(@RequestBody StoreRequest request, @PathVariable String userId) {
        try {
            return ResponseEntity.ok(new Result( true,"Store created successfully", storeService.createStore(request, userId)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Result(false, e.getMessage()));
        }
    }

    @PostMapping("/update/{storeId}/{userId}")
    public ResponseEntity<Result> updateStore(@RequestBody StoreRequest request, @PathVariable String storeId, @PathVariable String userId) {
        try {
            return ResponseEntity.ok(new Result(true, "Store updated successfully", storeService.updateStore(request, storeId, userId)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Result(false, e.getMessage()));
        }
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<Result> getStoreById(@PathVariable String storeId) {
        try {
            return ResponseEntity.ok(new Result(true, "Store retrieved successfully", storeService.getStoreById(storeId)));
        } catch (Exception e) {
            logger.error("Error retrieving store ", e.getMessage());
            return ResponseEntity.status(500).body(new Result(false, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Result> getAllStores(
            @PageableDefault(size = 10, sort = "brand", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            return ResponseEntity.ok(new Result(true, "Stores retrieved successfully", storeService.getAllStores(pageable)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Result(false, e.getMessage()));
        }
    }

    @DeleteMapping("/{storeId}/{userId}")
    public ResponseEntity<Result> deleteStore(@PathVariable String storeId, @PathVariable String userId) {
        try {
            storeService.deleteStore(storeId, userId);

            return ResponseEntity.ok(new Result(true, "Store deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Result(false, e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Result> getStoresByQuery(
            @RequestParam String query,
            @PageableDefault(size = 10, sort = "brand", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            return ResponseEntity.ok(new Result(true, "Stores retrieved successfully", storeService.searchStores(query, pageable)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Result(false, e.getMessage()));
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Result> getStoresByStatus(
            @RequestParam String status,
            @PageableDefault(size = 10, sort = "brand", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            return ResponseEntity.ok(new Result(true, "Stores retrieved successfully", storeService.getStoresByStatus(status, pageable)));
        } catch (Exception e) {
            logger.error("Error retrieving stores with status: {}", e.getMessage());
            return ResponseEntity.status(500).body(new Result(false, e.getMessage()));
        }
    }

}
