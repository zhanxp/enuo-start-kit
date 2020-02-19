package com.enuocms.android.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.util.AQUtility;
import com.enuocms.android.utils.DeviceUuidFactory;
import com.enuocms.android.utils.StringHelper;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class MainApplication extends Application {
    private static MainApplication mainApplication;
    private Activity stackTopActivity;			//栈顶活动
    private static AQuery mainAQuery;
    private String deviceId;
    private int versionCode = 0;

    public static MainApplication getMainApplication() {
        return mainApplication;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;

    }

    /*
	* @function		应用退出回调函数，此处进行善后工作
	* */
    public void onExit() {
//        //保存信息
//        if (UserInfo.getInstance().hasLogin()){
//            UserInfo.getInstance().saveToLocal();
//        }
//        AppInfo.getInstance().saveToLocal();

        //clear
        AQUtility.cleanCacheAsync(this);
    }

    public String getDeviceId() {
        if (StringHelper.empty(this.deviceId)) {
            DeviceUuidFactory dev = new DeviceUuidFactory(this);
            this.deviceId = dev.getDeviceUuid().toString().replace("-","");
            Log.i(Configs.STR_TAG, "deviceId:" + this.deviceId);
        }
        return this.deviceId;
    }

    public int getVersionCode() {
        if (this.versionCode <= 0 ) {
            try {
                PackageManager packageManager = getPackageManager();
                PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
                this.versionCode = packInfo.versionCode;
            } catch (Exception e) {

            }
        }
        return this.versionCode;
    }


    /*
    * @function		设置当前栈顶Activity
    * */
    public void setStackTopActivity(Activity stackTopActivity) {
        this.stackTopActivity = stackTopActivity;
    }


    @Override
    public void onLowMemory() {
        //当系统内存告急时，清空所有缓存图片
        BitmapAjaxCallback.clearCache();

        super.onLowMemory();
    }


    /*
* @function	获取应用级别的AQuery对象
* @date		2015-7-30
* */
    public static AQuery getMainAQuery() {
        if (mainAQuery == null){
            mainAQuery = new AQuery(getMainApplication());
        }
        return mainAQuery;
    }
}
