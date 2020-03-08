package com.enuocms.boot.controller;


import com.enuocms.account.model.Admin;
import com.enuocms.account.service.AdminService;
import com.enuocms.boot.config.shiro.ShiroUser;
import com.enuocms.boot.util.UserUtil;
import com.enuocms.business.model.Article;
import com.enuocms.business.model.Category;
import com.enuocms.business.service.ArticleService;
import com.enuocms.business.service.CategoryService;
import com.enuocms.core.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by zhanxiaoping on 2017/8/30.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    @RequestMapping("")
    public String index(Model model) {
        return "/admin/index";
    }

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

    @RequestMapping("category")
    public String catefgory(Model model) {
        List<Category> categorys = categoryService.list(null);
        model.addAttribute("list",categorys);
        return "/admin/category/index";
    }

    @RequestMapping("article")
    public String article(Model model,Integer pageIndex,Integer pageSize,Article query){
        query.setIsDelete(0);

        PageResult<Article> pagelist = articleService.pageList(pageIndex,pageSize,query);

        List<Category> categorys = categoryService.list(null);
        model.addAttribute("categorys",categorys);
        model.addAttribute("pageList",pagelist);

        return "/admin/article/index";
    }
}
