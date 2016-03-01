package com.epam.spring.core;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test.xml")
@SqlGroup({ @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:scripts/recreate-db.sql"),
        @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:scripts/insert-sample-data.sql") })
public abstract class AbstractIntegrationTest {
}
