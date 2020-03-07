
package com.enuocms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
//@MapperScan(basePackages = "com.enuocms.*.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}