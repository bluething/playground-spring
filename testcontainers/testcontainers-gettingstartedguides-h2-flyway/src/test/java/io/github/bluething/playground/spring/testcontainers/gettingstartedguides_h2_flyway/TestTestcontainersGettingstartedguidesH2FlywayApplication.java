package io.github.bluething.playground.spring.testcontainers.gettingstartedguides_h2_flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestTestcontainersGettingstartedguidesH2FlywayApplication {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>("postgres:latest");
	}

	public static void main(String[] args) {
		SpringApplication.from(TestcontainersGettingstartedguidesH2FlywayApplication::main).with(TestTestcontainersGettingstartedguidesH2FlywayApplication.class).run(args);
	}

}
