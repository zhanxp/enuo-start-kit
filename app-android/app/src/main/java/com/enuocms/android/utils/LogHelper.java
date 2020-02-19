package com.enuocms.android.utils;

import android.util.Log;

import com.enuocms.android.app.Configs;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class LogHelper {

    private static boolean DEBUG = Configs.FLAG_DEBUG;
    private static String  TAG = Configs.STR_TAG;

    //---------------Verbose------------------//
    public static void v(String info) {
        if(!DEBUG)
            return;
        Log.v(TAG, info);
    }

    public static void v(String tag, String info) {
        if(!DEBUG)
            return;
        Log.v(tag, info);
    }

    //---------------Debug------------------//
    public static void d(String info) {
        if(!DEBUG)
            return;
        Log.d(TAG, info);
    }

    public static void d(String tag, String info) {
        if(!DEBUG)
            return;
        Log.d(tag, info);
    }

    //---------------Info------------------//
    public static void i(String info) {
        if(!DEBUG)
            return;
        Log.i(TAG, info);
    }

    public static void i(String tag,String info) {
        if(!DEBUG)
            return;
        Log.i(tag, info);
    }

    //---------------Warn------------------//
    public static void w(String info) {
        if(!DEBUG)
            return;
        Log.w(TAG, info);
    }

    public static void w(String tag,String info) {
        if(!DEBUG)
            return;
        Log.w(tag, info);
    }

    //---------------Error------------------//
    public static void e(Exception e) {
        if(!DEBUG)
            return;
        Log.e(TAG, e.getClass().toString() + "," + e.getMessage() + ","
                + e.getStackTrace().toString());
        e.printStackTrace();
    }

    public static void e(String tag,String error) {
        if(!DEBUG)
            return;
        Log.e(tag, error);
    }
}
