package com.enuocms.boot.admin;

import com.enuocms.account.model.Admin;
import com.enuocms.account.service.AdminService;
import com.enuocms.boot.config.shiro.ShiroUser;
import com.enuocms.boot.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("profile")
    public String profile(Model model) {
        ShiroUser user = UserUtil.getCurrentUser();
        Admin profile = adminService.get(user.getId());
        model.addAttribute("model",profile);
        return "/admin/user/profile";
    }

    @RequestMapping("password")
    public String password(Model model) {
        return "/admin/user/password";
    }
}
