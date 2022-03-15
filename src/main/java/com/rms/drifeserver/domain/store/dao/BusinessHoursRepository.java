package com.rms.drifeserver.domain.store.dao;

import com.rms.drifeserver.domain.store.model.BusinessHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
    BusinessHours findByStoreId(Long storeId);
}
