package com.enuocms.business.service;

import com.enuocms.business.model.Article;
import com.enuocms.core.model.PageResult;
import com.enuocms.core.service.BaseServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhanxiaoping on 2020/3/7.
 * zhanxp@me.com
 */
@Component
@Transactional
public class ArticleService extends BaseServiceImpl<Article>  {

    public PageResult<Article> pageList(Integer pageIndex,Integer pageSize,Article query){
        if(query==null){
            query = new Article();
            query.setIsDelete(0);
        }
        PageResult<Article>  result = this.selectPage(query, pageIndex==null?1:pageIndex, pageSize==null?10:pageSize);
        return  result;
    }
}