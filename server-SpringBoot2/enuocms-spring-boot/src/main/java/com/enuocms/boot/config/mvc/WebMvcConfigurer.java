package com.enuocms.boot.config.mvc;

import com.enuocms.boot.interceptor.EnuoAuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhanxiaoping on 2017/8/30.
 *  zhanxp@me.com
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public EnuoAuthorizationInterceptor enuoAuthorizationInterceptor() {
        return new EnuoAuthorizationInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
//        if (!registry.hasMappingForPattern("/**")) {
//            registry.addResourceHandler("/**").addResourceLocations(
//                    RESOURCE_LOCATIONS);
//        }

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOrigins("*");
        super.addCorsMappings(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] swaggerPatterns = {"/swagger-ui.html/**", "/webjars/**", "/swagger-resources/**"};
        registry.addInterceptor(enuoAuthorizationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(swaggerPatterns)
                .excludePathPatterns("/account/**","/api/**","/error/**");

        super.addInterceptors(registry);
    }
}
