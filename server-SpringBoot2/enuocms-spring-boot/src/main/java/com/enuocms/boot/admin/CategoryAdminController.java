package com.enuocms.boot.admin;

import com.enuocms.business.model.Category;
import com.enuocms.business.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public String catefgory(Model model) {
        List<Category> categorys = categoryService.list(null);
        model.addAttribute("list",categorys);
        return "/admin/category/index";
    }
}
