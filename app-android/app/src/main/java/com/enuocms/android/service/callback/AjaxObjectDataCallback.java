package com.enuocms.android.service.callback;

import com.androidquery.callback.AjaxStatus;
import com.enuocms.android.app.APIConfigs;
import com.enuocms.android.service.entity.AjaxResult;
import com.enuocms.android.service.handler.AjaxObjectDataHandler;
import com.enuocms.android.utils.JSONHelper;

import org.json.JSONObject;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class AjaxObjectDataCallback<T> extends AjaxDataCallback {

    private AjaxObjectDataHandler<T> callbackHandler;
    private Class<T> clazz;

    public AjaxObjectDataCallback(AjaxObjectDataHandler<T> callbackHandler, Class<T> clazz) {
        this.callbackHandler = callbackHandler;
        this.clazz = clazz;
    }

    @Override
    public void callback(String url, JSONObject object, AjaxStatus status) {
        //1、解析AjaxResult
        AjaxResult ajaxResult = ajaxDebug(url, object);
        //2、解析AjaxData
        T ajaxObjectData = null;
        if (object != null) {
            JSONObject jsonObject = object.optJSONObject(APIConfigs.API_RESPONSE_KEY_DATA);
            ajaxObjectData = JSONHelper.toObject(jsonObject, clazz);
        }
        //3、调用callback
        callbackHandler.callback(ajaxResult, ajaxObjectData);
    }
}
