package com.enuocms.web.controller;


import com.enuocms.core.model.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("error")
@Slf4j
public class ErrorController {

    @RequestMapping(produces = "text/html",value = "")
    public String errorHtml500(Model model,Boolean json) {
        return "/error/500";
    }

    @RequestMapping(value = "")
    @ResponseBody
    public ServiceResult error500(HttpServletRequest request) {
        return new ServiceResult(false);
    }

    @RequestMapping(produces = "text/html",value = "403")
    public String errorHtml403(Model model,Boolean json) {
        return "/error/403";
    }

    @RequestMapping(value = "403")
    @ResponseBody
    public ServiceResult error403(HttpServletRequest request) {
        return new ServiceResult(false);
    }

    @RequestMapping(produces = "text/html",value = "404")
    public String errorHtml404(Model model,Boolean json) {
        return "/error/500";
    }

    @RequestMapping(value = "404")
    @ResponseBody
    public ServiceResult error404(HttpServletRequest request) {
        return new ServiceResult(false);
    }
}
