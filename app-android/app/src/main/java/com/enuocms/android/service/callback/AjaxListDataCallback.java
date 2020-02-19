package com.enuocms.android.service.callback;

import com.androidquery.callback.AjaxStatus;
import com.enuocms.android.app.APIConfigs;
import com.enuocms.android.service.entity.AjaxPageData;
import com.enuocms.android.service.entity.AjaxResult;
import com.enuocms.android.service.handler.AjaxListDataHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class AjaxListDataCallback<T> extends AjaxDataCallback {

    private AjaxListDataHandler<T> callbackHandler;
    private Class<T> clazz;

    public AjaxListDataCallback(AjaxListDataHandler<T> callbackHandler, Class<T> clazz) {
        this.callbackHandler = callbackHandler;
        this.clazz = clazz;
    }

    @Override
    public void callback(String url, JSONObject object, AjaxStatus status) {
        //1、解析AjaxResult
        AjaxResult ajaxResult = ajaxDebug(url, object);
        //2、解析AjaxData
        List<T> ajaxListData = null;
        if (object != null){
            JSONArray jsonArray = object.optJSONArray(APIConfigs.API_RESPONSE_KEY_DATA);
            ajaxListData = AjaxPageData.parseAjaxData(jsonArray, clazz);
        }
        //3、调用callback
        callbackHandler.callback(ajaxResult, ajaxListData);
    }
}
