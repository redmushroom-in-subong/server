package com.rms.drifeserver.domain.store.dao;

import com.rms.drifeserver.domain.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByregionCode(String regionCode);
}
