package com.example.demo.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class MySQL1JdbcConfiguration {
    // Cấu Hình DATA SOURCE 1
    @Bean("dataSourceProperties-1")
    @Primary
    @ConfigurationProperties("app.datasource.first")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("datasource-1")
    @Primary
    @ConfigurationProperties("app.datasource.first.configuration")
    public DataSource firstDataSource(@Qualifier("dataSourceProperties-1") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean("jdbc-1")
    public JdbcTemplate jdbcTemplate(@Qualifier("datasource-1") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("jdbc-1-tx")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("datasource-1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
