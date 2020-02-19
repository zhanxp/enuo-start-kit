package com.enuocms.core.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhanxiaoping on 2017/8/29.
 * zhanxp@me.com
 */
@Data
public class PageResult<T> {
    private List<T> items;
    private int total;
    private int pageIndex;
    private int pageSize;

    public PageResult(){
        this.items = new ArrayList<>();
    }


    public PageResult(int pageIndex, int pageSize, int total, List<T> items){
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.items = items;
    }
}
