package com.enuocms.boot.config.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Created by zhanxiaoping on 2017/8/30.
 * zhanxp@me.com
 */
@Configuration
public class FreeMarkerConfig {
    @Autowired
    protected freemarker.template.Configuration configuration;

    @Autowired
    protected FreeMarkerViewResolver freeMarkerViewResolver;

    @Autowired
    protected InternalResourceViewResolver internalResourceViewResolver;

    @PostConstruct
    public void  setSharedVariable(){
        configuration.setDateFormat("yyyy-MM-dd");
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        configuration.setTimeFormat("HH:mm:ss");
        configuration.setLocale(Locale.CHINA);

        //下面三句配置的就是我自己的freemarker的自定义标签，在这里把标签注入到共享变量中去就可以在模板中直接调用了
        configuration.setSharedVariable("shiro",  new ShiroTags());

        configuration.setNumberFormat("0.######");
        configuration.setWhitespaceStripping(true);
        configuration.setClassicCompatible(true);
        configuration.setTemplateUpdateDelayMilliseconds(0);
        configuration.setDefaultEncoding("UTF-8");
        //configuration.setAutoImports("/shared/common.ftl as p");

//        /**
//         * 配置Spring JSP的视图解析器
//         */
//        internalResourceViewResolver.setPrefix("");//解析前缀后XXX路径下的jsp文件
//        internalResourceViewResolver.setSuffix(".jsp");
//        internalResourceViewResolver.setOrder(1);
//        internalResourceViewResolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);

        /**
         * 配置Freemarker视图解析器
         */
//        freeMarkerViewResolver.setSuffix(".ftl");//解析后缀为html的
//        freeMarkerViewResolver.setCache(true); //是否缓存模板
//        freeMarkerViewResolver.setPrefix("");
//        freeMarkerViewResolver.setContentType("text/html; charset=UTF-8");
        freeMarkerViewResolver.setViewClass(ExtFreeMarkerView.class);
//        springResolver.setOrder(0);
    }
}
