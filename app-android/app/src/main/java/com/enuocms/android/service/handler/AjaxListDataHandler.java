package com.enuocms.android.service.handler;

import com.enuocms.android.service.entity.AjaxResult;

import java.util.List;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public interface AjaxListDataHandler<T> {
    public void callback(AjaxResult result, List<T> data);
}
