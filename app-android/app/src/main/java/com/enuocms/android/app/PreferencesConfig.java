package com.enuocms.android.app;

import android.content.SharedPreferences;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class PreferencesConfig {
    public static SharedPreferences sharedPreferences;

    public final static String APP_IS_FIRST_LAUNCH_KEY = "is_first";                        //app是否首次启动
    public final static String APP_LAST_UPDATE_CHECK_TIME_KEY = "last_check_update_time";   //app上次更新时间
    public final static String APP_INFO_KEY = "app_info_key";                               //AppInfo存储键值
    public final static String APP_SESSION_KEY = "app_session_key";                         //UserInfo存储键值

    public static void getSharedPreferences(){
        if (sharedPreferences == null){
            sharedPreferences = MainApplication.getMainApplication().getSharedPreferences(Configs.PREFS_NAME, 0);
        }
    }

    /*
    * @function     根据key值，从shared中取出对应类型的value值
    * */
    public static String get(final String key) {
        getSharedPreferences();

        return sharedPreferences.getString(key,"");
    }

    /*
    * @function     根据key值，从shared中设置对应类型的value值
    * */
    public static void set(final String key,final String value) {
        getSharedPreferences();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
