package com.enuocms.android.service.callback;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.enuocms.android.app.Configs;
import com.enuocms.android.service.entity.AjaxResult;
import com.enuocms.android.service.handler.AjaxDataHandler;
import com.enuocms.android.utils.LogHelper;

import org.json.JSONObject;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class AjaxDataCallback extends AjaxCallback<JSONObject> {
    /*
    * @function         网络请求调试
    * @url              请求地址
    * @object           响应数据
    * */
    protected AjaxResult ajaxDebug(final String url, final JSONObject object) {
        AjaxResult ajaxResult = null;

        if (Configs.FLAG_DEBUG){    //调试模式
            String request = url.replace(' ','%');
            LogHelper.v(request);

            String response = "null";
            if (object != null){
                response = object.toString();
            }
            LogHelper.v(response);
        }

        ajaxResult = AjaxResult.parseAjaxResult(object);

        return ajaxResult;
    }

    public  AjaxDataCallback(){

    }

    private AjaxDataHandler callbackHandler;
    public AjaxDataCallback(AjaxDataHandler callbackHandler) {
        this.callbackHandler = callbackHandler;
    }

    @Override
    public void callback(String url, JSONObject object, AjaxStatus status) {
        //1、解析AjaxResult
        AjaxResult ajaxResult = ajaxDebug(url, object);
        //2、解析AjaxData
        //3、调用callback
        callbackHandler.callback(ajaxResult, object);
    }
}
