
package com.enuocms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
//@MapperScan(basePackages = "com.enuocms.*.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}