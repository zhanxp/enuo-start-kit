package com.enuocms.boot.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.enuocms.boot.util.ApiUserUtil;
import com.enuocms.boot.util.ResponseUtil;
import com.enuocms.boot.vo.ApiUserVo;
import com.enuocms.core.model.ServiceResult;
import com.enuocms.core.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */
@Slf4j
public class EnuoApiInterceptor  implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(httpServletRequest.getMethod().equals("OPTIONS")){
            return true;
        }

        String ticket = httpServletRequest.getHeader("ticket");
        if(StringUtils.isEmpty(ticket)){
            Map<String, String[]> params = httpServletRequest.getParameterMap();
            String[] values = params.get("ticket");
            if (values != null && values.length > 0) {
                ticket = values[0];
            }
        }

        if (StringUtils.isEmpty(ticket)) {
            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("enuo_ticket")) {
                        ticket = cookie.getValue();
                    }
                }
            }
        }

        ApiUserVo user = null;
        if(!StringUtils.isEmpty(ticket)) {
            String userStr = redisService.get(ticket);
            user = JSONObject.parseObject(userStr,ApiUserVo.class);
        }

        if(user == null){
            ServiceResult.ResultCode errorCode =  ServiceResult.ResultCode.NOT_LOGIN;
            ServiceResult result = new ServiceResult(errorCode,null);
            ResponseUtil.outData(httpServletResponse,result);
            return false;
        }

        ApiUserUtil.setUserInfo(user);
        return  true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
