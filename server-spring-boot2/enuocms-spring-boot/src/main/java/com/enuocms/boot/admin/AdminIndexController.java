package com.enuocms.boot.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhanxiaoping on 2017/8/30.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @RequestMapping("")
    public String index(Model model) {
        return "/admin/index";
    }
}
