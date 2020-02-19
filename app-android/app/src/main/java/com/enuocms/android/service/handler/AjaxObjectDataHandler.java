package com.enuocms.android.service.handler;

import com.enuocms.android.service.entity.AjaxResult;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public interface AjaxObjectDataHandler<T> {
    public void callback(AjaxResult result, T data);
}
