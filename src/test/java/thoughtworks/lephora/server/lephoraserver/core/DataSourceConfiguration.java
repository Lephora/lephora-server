package thoughtworks.lephora.server.lephoraserver.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import javax.sql.DataSource;

@TestConfiguration
public class DataSourceConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public PostgreSQLContainer<?> postgresqlcontainer() {
        return new PostgreSQLContainer<>("postgres:13.2-alpine")
                .waitingFor(Wait.forListeningPort());
    }

    @Bean
    @FlywayDataSource
    public DataSource dataSource(PostgreSQLContainer<?> postgreSqlContainer) {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(postgreSqlContainer.getJdbcUrl());
        hikariConfig.setUsername(postgreSqlContainer.getUsername());
        hikariConfig.setPassword(postgreSqlContainer.getPassword());
        return new HikariDataSource(hikariConfig);
    }
}
