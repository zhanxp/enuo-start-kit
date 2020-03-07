package com.enuocms.boot.controller;


import com.enuocms.account.model.Admin;
import com.enuocms.account.service.AdminService;
import com.enuocms.boot.config.shiro.ShiroUser;
import com.enuocms.boot.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhanxiaoping on 2017/8/30.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("")
    public String index(Model model) {
        ShiroUser user = UserUtil.getCurrentUser();

        Admin profile = adminService.get(user.getId());
        model.addAttribute("profile",profile);

        return "/admin/profile";
    }
}
