package com.rms.drifeserver.integrationTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"classpath:data/review_keyword_type_insert.sql"})
public abstract class IntegrationTest {

}
