package com.enuocms.web.advice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@ControllerAdvice
public class ResourceUrlAdvice {

    @Autowired
    ResourceUrlProvider resourceUrlProvider;

    @ModelAttribute("spring_url")
    public ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }
}

