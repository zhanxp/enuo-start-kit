package com.enuocms.android.service;

import android.app.Activity;

import com.enuocms.android.app.APIConfigs;
import com.enuocms.android.entity.UserInfo;
import com.enuocms.android.service.handler.AjaxObjectDataHandler;

import java.util.Map;

/**
 * @author 占小平
 * @function
 * @date 2017/9/28
 */
public class AdminService  extends ABaseNetworkService {

    public AdminService(Activity activity) {
        super(activity);
    }

    public void login(String username, String password, final AjaxObjectDataHandler<UserInfo> callbackHandler){
        //1、解析url
        String url = APIConfigs.API_URL_ROOT + "login";

        //2、填充params
        Map<String,Object> params = getAjaxParams();
        params.put("username",username);
        params.put("password",password);

        //3、提交网络请求
        submitAjaxObjectDataGetRequest(url, params, callbackHandler,UserInfo.class);
    }
}
