package com.rms.drifeserver.domain.store.dao;

import com.rms.drifeserver.domain.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    //TODO find method 자동생성 규칙 , entity findBy+ entity's field name with 첫번째 대문자 -> 그 외에는 오류나서 서버실행 안됨
    Store findByKakaoPlaceId(Long storeId);

}
