package com.enuocms.boot.controller;

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
@RequestMapping("/")
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;
    
    @RequestMapping("")
    public String index(Model model,Integer pageIndex,Integer pageSize) {
        PageResult<Article> pageList = articleService.pageList(pageIndex,pageSize,null);
        List<Category> categorys = categoryService.list(null);

        model.addAttribute("pageList",pageList);
        model.addAttribute("categorys",categorys);
    	return "/home/index";
	}

    @RequestMapping("about")
    public String about(Model model) {
        List<Category> categorys = categoryService.list(null);
        model.addAttribute("categorys",categorys);
        model.addAttribute("message","Your application description page.");
        return "/home/about";
    }
}
