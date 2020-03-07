package com.enuocms.core.mapper;

import java.lang.annotation.*;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SqlMapper {
    String value() default "";
}