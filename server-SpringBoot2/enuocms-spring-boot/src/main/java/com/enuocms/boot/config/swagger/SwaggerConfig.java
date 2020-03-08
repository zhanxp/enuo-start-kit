package com.enuocms.boot.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */
@Configuration
@Profile("development")
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        // 全局参数
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("ticket").description("user ticket")
                .modelRef(new ModelRef("string")).parameterType("query")
                .required(false).build();
        parameters.add(parameterBuilder.build());

        // 应用信息
        springfox.documentation.service.ApiInfo apiInfo = new ApiInfoBuilder().version("1.0.0").title("Enuo Service API").description("Documentation Enuo Service API v1.0")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select()
                //.apis(RequestHandlerSelectors.basePackage("com.enuocms.boot.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any()).build().globalOperationParameters(parameters);

        return docket;
    }
}
