package com.enuocms.boot.controller;

import com.enuocms.business.category.Category;
import com.enuocms.business.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("category")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public String index(Model model,Category query) {
        List<Category> list = categoryService.select(query);
        model.addAttribute("list",list);
        return "/category/index";
    }
}
