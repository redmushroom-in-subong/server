package com.rms.drifeserver.domain.search.dao;

import com.rms.drifeserver.domain.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchQueryFactory extends JpaRepository<Store, Long>{
    @Query("select s from Store s where s.id in(:inQuery)")
    List<Store> findSearchResult(String inQuery);
}
