package com.enuocms.boot.admin;

import com.enuocms.business.model.Article;
import com.enuocms.business.model.Category;
import com.enuocms.business.service.ArticleService;
import com.enuocms.business.service.CategoryService;
import com.enuocms.core.model.PageResult;
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
@RequestMapping("/admin/article")
public class ArticleAdminController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public String article(Model model, Integer pageIndex, Integer pageSize, Article query){
        query.setIsDelete(0);

        PageResult<Article> pagelist = articleService.pageList(pageIndex,pageSize,query);

        List<Category> categorys = categoryService.list(null);
        model.addAttribute("categorys",categorys);
        model.addAttribute("pageList",pagelist);

        return "/admin/article/index";
    }
}
