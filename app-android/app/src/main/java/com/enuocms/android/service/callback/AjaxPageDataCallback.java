package com.enuocms.android.service.callback;

import com.androidquery.callback.AjaxStatus;
import com.enuocms.android.service.entity.AjaxPageData;
import com.enuocms.android.service.entity.AjaxResult;
import com.enuocms.android.service.handler.AjaxPageDataHandler;

import org.json.JSONObject;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class AjaxPageDataCallback<T> extends AjaxDataCallback {

    private AjaxPageDataHandler<T> callbackHandler;
    private Class<T> clazz;

    public AjaxPageDataCallback(AjaxPageDataHandler<T> callbackHandler, Class<T> clazz) {
        this.callbackHandler = callbackHandler;
        this.clazz = clazz;
    }

    @Override
    public void callback(String url, JSONObject object, AjaxStatus status) {
        //1、解析AjaxResult
        AjaxResult ajaxResult = ajaxDebug(url, object);
        //2、解析AjaxPage
        AjaxPageData<T> ajaxPageData = AjaxPageData.parseAjaxPage(object,clazz);
        //3、调用callback
        callbackHandler.callback(ajaxResult, ajaxPageData);
    }
}
