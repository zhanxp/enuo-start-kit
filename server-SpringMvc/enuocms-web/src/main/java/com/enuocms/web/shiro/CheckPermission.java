package com.enuocms.web.shiro;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})//适用的地方 有 方法上  类上等
public  @interface CheckPermission {
    boolean value() default true;
}

