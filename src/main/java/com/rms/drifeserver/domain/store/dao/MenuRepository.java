package com.rms.drifeserver.domain.store.dao;

import com.rms.drifeserver.domain.store.model.Menu;
import com.rms.drifeserver.domain.store.service.dto.response.MenuResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("select m from Menu m where m.store.id = :storeId")
    List<MenuResponse> findAllByStoreId(@Param("storeId") Long storeId);

    @Query("select m from Menu m where m.store.id = :storeId and m.id = :menuId")
    Menu findByIdAndStoreId(@Param("menuId") Long menuId, @Param("storeId") Long storeId);
}
