package com.enuocms.boot.controller;

import com.enuocms.business.model.Article;
import com.enuocms.business.model.Category;
import com.enuocms.business.service.ArticleService;
import com.enuocms.business.service.CategoryService;
import com.enuocms.core.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by zhanxiaoping on 2017/8/30.
 * zhanxp@me.com
 */

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/{category}")
    public String index(Model model,@PathVariable int category, Integer pageIndex,Integer pageSize,Article query) {

        query.setIsDelete(0);
        query.setCategoryId(category);

        PageResult<Article> pagelist = articleService.pageList(pageIndex,pageSize,query);
        List<Category> categorys = categoryService.list(null);

        model.addAttribute("pageList",pagelist);
        model.addAttribute("categorys",categorys);
        return "/article/index";
    }

    @RequestMapping("add")
    public String add(Model model) {
        Article ent = new Article();
        model.addAttribute("model",ent);
        return "/article/add";
    }

    @RequestMapping("edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Article ent = articleService.get(id);
        model.addAttribute("model",ent);
        return "/article/edit";
    }

    @RequestMapping("detail/{id}")
    public String details(Model model, @PathVariable int id) {
        Article ent = articleService.get(id);
        List<Category> categorys = categoryService.list(null);

        model.addAttribute("model",ent);
        model.addAttribute("categorys",categorys);
        return "/article/detail";
    }
}
