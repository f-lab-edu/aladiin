package aladiin.couponapi.controller;

import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MySQLTestContainer{

    @Container
    public static final JdbcDatabaseContainer mySQLContainer = new MySQLContainer("mysql:8")
            .withDatabaseName("testDB")
            .withUsername("testUser")
            .withPassword("testPassword")
            .withInitScript("init.sql");
}
