package com.enuocms.boot.api;

import com.enuocms.business.model.Article;
import com.enuocms.business.service.ArticleService;
import com.enuocms.core.model.PageResult;
import com.enuocms.core.model.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */
@RestController
@RequestMapping("/api/article")
@Api(value = "ArticleApiController", tags = "ArticleApiController")
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "分页文章列表",notes = "")
    @GetMapping("/pageList")
    public ServiceResult<PageResult<Article>> pageList(Integer isDelete, Integer categoryId, String title, Integer pageIndex, Integer pageSize) {
        Article query = new Article();
        query.setCategoryId(categoryId);
        query.setTitle(title);
        query.setIsDelete(isDelete);
        PageResult<Article> pagelist = articleService.pageList(pageIndex,pageSize,query);
        return new ServiceResult(pagelist);
    }
}
