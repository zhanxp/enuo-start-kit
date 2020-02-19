package com.enuocms.android.entity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.enuocms.android.app.Configs;
import com.enuocms.android.app.MainApplication;
import com.enuocms.android.app.PreferencesConfig;
import com.enuocms.android.utils.JSONHelper;
import com.enuocms.android.utils.StringHelper;

import java.util.Date;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class AppInfo {

    private static AppInfo sInstance = null;

    public String ID;
    public Date lastCodeTime;
    public Date appTime;
    public String callID;
    public int changGuanID;

    public int versionCode;		//应用版本号(程序)
    public String versionName;	//应用版本名(用户)

    public AppInfo() {

    }

    /*
    * @function		更新应用动态信息
    * */
    public void updateAppInfo(){
        this.ID = Configs.kAppID;

        //读取应用版本信息
        MainApplication applicationContext = MainApplication.getMainApplication();
        PackageManager pm = applicationContext.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(applicationContext.getPackageName(), 0);
            this.versionName = packageInfo.versionName;
            this.versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * @function		获取本地应用信息
    * */
    public static AppInfo getInstance() {
        if (sInstance == null) {
            sInstance = getFromLocal();
        }
        return sInstance;
    }

    /*
    * @function		同步本地应用信息
    * */
    public static AppInfo updateInstance() {
        sInstance = getFromLocal();
        return sInstance;
    }

    /*
    * @function		存储本地应用信息
    * */
    public void saveToLocal() {
        String appInfoValue = JSONHelper.fromObject(this);
        PreferencesConfig.set(PreferencesConfig.APP_INFO_KEY,appInfoValue);
    }

    /*
    * @function		收集本地应用信息
    * */
    private static AppInfo getFromLocal() {

        String appInfoValue = PreferencesConfig.get(PreferencesConfig.APP_INFO_KEY);

        if (StringHelper.empty(appInfoValue)) {
            AppInfo appInfo = new AppInfo();
            appInfo.updateAppInfo();

            appInfo.appTime = new Date();

            return appInfo;
        }else{

            AppInfo appInfo = JSONHelper.toObject(appInfoValue, AppInfo.class);
            appInfo.updateAppInfo();

            return appInfo;
        }
    }

}