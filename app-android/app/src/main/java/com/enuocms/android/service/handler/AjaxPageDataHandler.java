package com.enuocms.android.service.handler;

import com.enuocms.android.service.entity.AjaxPageData;
import com.enuocms.android.service.entity.AjaxResult;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public interface AjaxPageDataHandler<T> {
    public void callback(AjaxResult result, AjaxPageData<T> data);
}
