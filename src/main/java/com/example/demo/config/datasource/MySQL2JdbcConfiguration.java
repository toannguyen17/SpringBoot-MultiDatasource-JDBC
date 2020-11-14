package com.example.demo.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class MySQL2JdbcConfiguration {
    // Cấu Hình DATA SOURCE 2
    @Bean("dataSourceProperties-2")
    @ConfigurationProperties("app.datasource.second")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("datasource-2")
    @ConfigurationProperties("app.datasource.second.configuration")
    public DataSource firstDataSource(@Qualifier("dataSourceProperties-2") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean("jdbc-2")
    public JdbcTemplate jdbcTemplate(@Qualifier("datasource-2") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("jdbc-2-tx")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("datasource-2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
