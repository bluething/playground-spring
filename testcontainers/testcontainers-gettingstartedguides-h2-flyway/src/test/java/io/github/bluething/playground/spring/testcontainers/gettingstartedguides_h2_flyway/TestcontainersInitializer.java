package io.github.bluething.playground.spring.testcontainers.gettingstartedguides_h2_flyway;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

public class TestcontainersInitializer  implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.2-alpine");

    static {
        Startables.deepStart(postgres).join();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "spring.test.database.replace=none",
                "spring.datasource.url=" + postgres.getJdbcUrl(),
                "spring.datasource.username=" + postgres.getUsername(),
                "spring.datasource.password=" + postgres.getPassword()
        ).applyTo(applicationContext.getEnvironment());
    }
}
