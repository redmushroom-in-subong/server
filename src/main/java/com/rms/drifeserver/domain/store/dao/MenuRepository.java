package com.rms.drifeserver.domain.store.dao;

import com.rms.drifeserver.domain.store.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByStoreId(Long storeId);

    Menu findByMenuIdAndStoreId(Long menuId, Long storeId);
}
