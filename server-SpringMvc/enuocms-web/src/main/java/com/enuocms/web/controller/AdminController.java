package com.enuocms.web.controller;


import com.enuocms.account.admin.Admin;
import com.enuocms.account.admin.AdminService;
import com.enuocms.web.shiro.ShiroUser;
import com.enuocms.web.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("admin")
@Slf4j
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
