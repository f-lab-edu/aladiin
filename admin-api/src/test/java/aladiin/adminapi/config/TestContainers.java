package aladiin.adminapi.config;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestContainers {
    private static final String REDIS_IMAGE = "redis:7.0.8-alpine";
    private static final String MYSQL_IMAGE = "mysql:8";
    private static final int REDIS_PORT = 7000;

    @Container
    public static final GenericContainer redisTestContainer = new GenericContainer(REDIS_IMAGE)
            .withReuse(true);
    @Container
    public static final JdbcDatabaseContainer mySQLTestContainer = new MySQLContainer(MYSQL_IMAGE)
            .withInitScript("init.sql");
    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.driver-class-name", mySQLTestContainer::getDriverClassName);
        registry.add("spring.datasource.url", mySQLTestContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLTestContainer::getUsername);
        registry.add("spring.datasource.password", mySQLTestContainer::getPassword);
    }
    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redisTestContainer::getHost);
    }
}
