package com.rms.drifeserver.integrationTest;

import com.rms.drifeserver.domain.store.dao.StoreRepository;
import com.rms.drifeserver.domain.store.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SetupStoreIntegrationTest extends SetupUserIntegrationTest {

    @Autowired
    protected StoreRepository storeRepository;

    protected Long storeId;

    protected Store store;

    @BeforeEach
    void setUpStore() {
        store = new Store("test-phone", "test-addressName", "1.1", "1.1",
                "test-regionCode", "test-storeName", "test-storeDesc", "test-snsAddress");
        storeRepository.save(store);
        storeId = store.getId();
    }
}
