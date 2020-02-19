package com.enuocms.boot.config.mybatis;

/**
 * Created by zhanxiaoping on 2017/10/16.
 * zhanxp@me.com
 */

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.enuocms.account.*.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisAccountConfig {
    @Autowired
    @Qualifier("dataSourceAccount")
    private DataSource dataSourceAccount;

    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceAccount); // 使用titan数据源, 连接titan库

        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); // 使用上面配置的Factory
        return template;
    }
}
