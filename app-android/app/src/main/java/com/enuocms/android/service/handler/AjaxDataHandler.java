package com.enuocms.android.service.handler;

import com.enuocms.android.service.entity.AjaxResult;

import org.json.JSONObject;

/**
 * @author peter <zhanxp@me.com>
 * @function
 * @date 2017/9/28
 */
public interface AjaxDataHandler {
    public void callback(AjaxResult result, JSONObject data);
}
