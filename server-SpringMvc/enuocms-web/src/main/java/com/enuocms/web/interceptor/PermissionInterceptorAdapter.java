package com.enuocms.web.interceptor;

import com.enuocms.web.shiro.ShiroUser;
import com.enuocms.web.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Aspect
@Component
@Slf4j
public class PermissionInterceptorAdapter extends HandlerInterceptorAdapter {

	@Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        ShiroUser user = UserUtil.getCurrentUser();
        if(user==null){

            String requestType = request.getHeader("X-Requested-With");
            boolean isAjax =  "XMLHttpRequest".equals(requestType);
            if(isAjax){
                request.getRequestDispatcher("/error/403").forward(request, response);
            }else{
                request.getRequestDispatcher("/account/login").forward(request, response);
            }
            return false;
        }else{
            return true;
        }
    }
}
