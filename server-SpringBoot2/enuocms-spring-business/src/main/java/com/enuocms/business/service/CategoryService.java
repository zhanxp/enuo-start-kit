package com.enuocms.business.service;

import com.enuocms.business.model.Category;
import com.enuocms.core.service.BaseServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhanxiaoping on 2020/3/7.
 * zhanxp@me.com
 */
@Component
@Transactional
public class CategoryService extends BaseServiceImpl<Category> {
    public List<Category> list(Category query){
        if(query==null){
            query = new Category();
            query.setIsDelete(0);
        }
        return dao.select(query);
    }
}
