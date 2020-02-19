package com.enuocms.boot.config.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by zhanxiaoping on 2017/10/16.
 * zhanxp@me.com
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "dataSourceAccount")
    @ConfigurationProperties(prefix = "spring.datasource.account")
    public DataSource dataSourceAccount() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourceBusiness")
    @ConfigurationProperties(prefix = "spring.datasource.business")
    public DataSource dataSourceBusiness() {
        return DataSourceBuilder.create().build();
    }
}
