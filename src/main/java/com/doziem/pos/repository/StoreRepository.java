package com.doziem.pos.repository;

import com.doziem.pos.enums.StoreStatus;
import com.doziem.pos.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, String> {

    Optional<Store> findByStoreId(String storeId);

    @Query("""
        SELECT s FROM Store s
        WHERE LOWER(s.brand) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(s.description) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(s.storeType) LIKE LOWER(CONCAT('%', :query, '%'))
        """)
    Page<Store> searchStores(@Param("query") String query, Pageable pageable);

    Page<Store> findByStatus(StoreStatus status, Pageable pageable);
}
