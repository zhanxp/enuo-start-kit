package com.enuocms.boot.api;

import com.enuocms.business.model.Category;
import com.enuocms.business.service.CategoryService;
import com.enuocms.core.model.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */
@RestController
@RequestMapping("/api/category")
@Api(value = "CategoryApiController", tags = "CategoryApiController")
public class CategoryApiController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "分类列表",notes = "")
    @GetMapping("/list")
    public ServiceResult<List<Category>> list(Integer isDelete) {
        Category query = new Category();
        query.setIsDelete(isDelete);
        List<Category> list = categoryService.list(query);
        return new ServiceResult(list);
    }
}
