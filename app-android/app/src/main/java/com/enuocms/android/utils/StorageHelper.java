package com.enuocms.android.utils;

import android.content.Context;
import android.os.Environment;

import com.enuocms.android.app.MainApplication;

import java.io.File;
import java.io.IOException;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class StorageHelper {

    /*
    * @function		获取应用相关缓存目录，如果存在外存，则为外部缓存；否则，为内部缓存；
    * */
    public static File getCacheDirectory() {

        File appCacheDir;

        if (existExternalStorage()) {
            appCacheDir = getExternalCacheDirectory();
        }else {
            appCacheDir = getInnerCacheDirectory();
        }

        return appCacheDir;
    }

    /*
    * @function		获取内部存储缓存目录
    * */
    public static File getInnerCacheDirectory() {
        File appCacheDir;

        Context context = MainApplication.getMainApplication();
        appCacheDir = context.getCacheDir();

        return appCacheDir;
    }

    /*
    * @function		获取外部存储缓存目录
    * */
    public static File getExternalCacheDirectory() {
        Context context = MainApplication.getMainApplication();

        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "items");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
            }
        }
        return appCacheDir;
    }

    /*
    * @function		判断内存卡是否存在
    * */
    public static boolean existExternalStorage() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }else {
            return false;
        }
    }
}
