package com.enuocms.boot.controller;

import com.enuocms.business.model.Article;
import com.enuocms.business.service.ArticleService;
import com.enuocms.core.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhanxiaoping on 2017/8/30.
 * zhanxp@me.com
 */

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("")
    public String index(Model model, Integer pageIndex,Integer pageSize,Article quert) {

        PageResult<Article> pagelist = articleService.selectPage(quert,
                pageIndex==null?1:pageIndex,
                pageSize==null?10:pageSize);

        model.addAttribute("pageList",pagelist);
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

    @RequestMapping("details/{id}")
    public String details(Model model, @PathVariable int id) {
        Article ent = articleService.get(id);
        model.addAttribute("model",ent);
        return "/article/details";
    }
}
