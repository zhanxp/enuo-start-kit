package com.enuocms.boot.interceptor;

import com.enuocms.boot.config.shiro.ShiroUser;
import com.enuocms.boot.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */

@Slf4j
public  class EnuoAuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate template;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(httpServletRequest.getMethod().equals("OPTIONS")){
            return true;
        }

        ShiroUser user = UserUtil.getCurrentUser();
        if(user==null){
            httpServletRequest.getRequestDispatcher("/account/login").forward(httpServletRequest, httpServletResponse);
            return false;
        }

        return  true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
