package com.enuocms.android.entity;

import com.enuocms.android.app.PreferencesConfig;
import com.enuocms.android.utils.JSONHelper;
import com.enuocms.android.utils.StringHelper;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class UserInfo  implements ITableData {

    private static UserInfo sInstance = null;

    public long id;
    public String ticket;
    public String name;


    public UserInfo() {
    }

    /*
    * @function		重置本地用户信息（并存储用户信息）
    * */
    public static void resetInstance() {
        setInstance(null);
    }

    /*
    * @function		设置本地用户信息（并存储用户信息）
    * */
    public static void setInstance(UserInfo userInfo) {
        if (userInfo == null){
            clear();
            sInstance = new UserInfo();
        }else{
            sInstance = userInfo;
        }
        //1、存储用户信息
        sInstance.saveToLocal();
    }

    /*
    * @function		获取本地用户信息
    * */
    public static UserInfo getInstance() {
        if (sInstance == null) {
            sInstance = UserInfo.getFromLocal();
        }
        return sInstance;
    }

    /*
    * @function		存储本地用户信息
    * */
    public void saveToLocal() {
        String seriaziedString = JSONHelper.fromObject(this);
        PreferencesConfig.set(PreferencesConfig.APP_SESSION_KEY,seriaziedString);
    }

    /*
    * @function		收集本地用户信息
    * */
    private static UserInfo getFromLocal() {
        String appSessionValue = PreferencesConfig.get(PreferencesConfig.APP_SESSION_KEY);

        UserInfo userInfo = JSONHelper.toObject(appSessionValue, UserInfo.class);

        if (userInfo == null) {
            userInfo = new UserInfo();
        }

        return userInfo;
    }

    /*
    * @function		判断用户是否已经登录
    * */
    public boolean hasLogin() {
        if (StringHelper.empty(this.ticket) || this.id == 0 ) {
            return false;
        }
        return true;
    }


    /*
    * @function		判断用户登录状态
    * */
    public static boolean isUserLogin() {
        return UserInfo.getInstance().hasLogin();
    }

    /*
    * @function		清空本地用户信息
    * */
    private static void clear() {
        PreferencesConfig.set(PreferencesConfig.APP_SESSION_KEY,"");
    }
}