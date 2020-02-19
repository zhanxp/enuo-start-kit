package com.enuocms.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Slf4j
public class CommonExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object object,
                                         Exception exception) {

        log.error(exception.getMessage(),exception);

        String requestType = request.getHeader("X-Requested-With");
        boolean isAjax =  "XMLHttpRequest".equals(requestType);
        return new ModelAndView("redirect:/error?json="+isAjax);
    }
}
