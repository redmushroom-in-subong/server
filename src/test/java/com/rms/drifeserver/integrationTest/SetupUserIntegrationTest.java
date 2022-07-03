package com.rms.drifeserver.integrationTest;

import com.rms.drifeserver.domain.oauth.entity.ProviderType;
import com.rms.drifeserver.domain.oauth.entity.RoleType;
import com.rms.drifeserver.domain.user.dao.UserRepository;
import com.rms.drifeserver.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class SetupUserIntegrationTest extends IntegrationTest {

    @Autowired
    protected UserRepository userRepository;

    protected Long userId;

    protected User user;

    @BeforeEach
    void setupUser() {
        User testUser = new User(
                "social-id",
                "test-name",
                "test-email",
                null,
                "Y",
                "test-img",
                ProviderType.LOCAL,
                RoleType.USER,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        testUser.setRegionCode("test-code");

        user = userRepository.save(testUser);
        userId = user.getId();
    }

}
