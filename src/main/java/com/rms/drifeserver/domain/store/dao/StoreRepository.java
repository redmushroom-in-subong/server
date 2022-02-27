package com.rms.drifeserver.domain.store.dao;

import com.rms.drifeserver.domain.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
