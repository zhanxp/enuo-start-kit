package com.enuocms.boot.controller;

import com.enuocms.boot.vo.AccountVo;
import com.enuocms.business.model.Article;
import com.enuocms.business.service.ArticleService;
import com.enuocms.core.model.PageResult;
import com.enuocms.core.model.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */

@RestController
@RequestMapping("/api")
@Api(value = "ApiController", tags = "API")
public class ApiController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "账户登录",notes = "")
    @PostMapping("/login")
    public ServiceResult login(@RequestBody AccountVo adminVo){
        return  new ServiceResult(false);
    }

    @ApiOperation(value = "分页文章列表",notes = "")
    @GetMapping("/article/pageList")
    public ServiceResult articlePageList(Integer isDelete,Integer categoryId,String title, Integer pageIndex,Integer pageSize) {
        Article query = new Article();
        query.setCategoryId(categoryId);
        query.setTitle(title);
        query.setIsDelete(isDelete);
        PageResult<Article> pagelist = articleService.pageList(pageIndex,pageSize,query);
        return new ServiceResult(pagelist);
    }
}
