package com.example.multidb.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
public class DataSourceProperties {

    //  스프링부트의 기본 jdbc 로 hikari를 사용하고 있다.

    @Primary //주가 되는 DataSource를 지정해 줍니다.
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master") // properties 속성을 지정해 줍니다
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaverDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
