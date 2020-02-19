package com.enuocms.android.utils;

import android.location.Location;
import android.net.Uri;
import android.os.Environment;

import com.enuocms.android.R;
import com.enuocms.android.app.MainApplication;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class CommonHelper {

    /*
    * @function		根据当前系统时间，计算默认Uri
    * */
    public static Uri getDefaultUri(){
        File file = getDefaultFile();
        return Uri.fromFile(file);
    }

    /*
    * @function		根据当前系统时间，计算默认File
    * */
    public static File getDefaultFile(){
        File file = new File(StorageHelper.getCacheDirectory(),
                "tmp_avatar_"
                        + String.valueOf(System.currentTimeMillis())
                        + ".jpg");
        return file;
    }

    /*
    * @function		根据当前系统时间，计算默认音频File
    * */
    public static File getDefaultAudioFile(){
        File file = new File(StorageHelper.getCacheDirectory(),
                "tmp_avatar_"
                        + String.valueOf(System.currentTimeMillis())
                        + ".wav");
        return file;
    }

    public static final int IMAGE_REQUEST_CODE = 0;
    public static final int CAMERA_REQUEST_CODE = 1;



    /*
    * @function		根据资源标示，获取字符串
    * */
    public static String getResourceString(int resourceId) {
        return MainApplication.getMainApplication().getString(resourceId);
    }

    /*
    * @function		线程等待nInterval个200ms
    * */
    public static void waitInterval(int nInterval) {
        try {
            Thread.sleep(200 * nInterval);
        } catch (Exception e) {
        }
    }

    public static String subStringTime(String str) {
        String timeM = str.substring(6, 7);
        String month = MainApplication.getMainApplication().getString(R.string.month);
        String day = MainApplication.getMainApplication().getString(R.string.day);
        String timeD = str.substring(8, 10);
        String endMinute = str.substring(11, 16);
        String result = timeM + month + timeD + day + endMinute;
        return result;
    }

    public static long getDaynumber(String firstdate, String enddate) {
        long daynumber = 1;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date first = sdf.parse(firstdate);
            Date end = sdf.parse(enddate);
            daynumber = end.getTime() - first.getTime();
            daynumber = daynumber / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return daynumber;
    }

    public static String ChangeTime(int time) {
        String hour = time / 3600 + "";
        int b = time % 3600;
        String fen = b / 60 + "";
        String miao = b % 60 + "";
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        if (fen.length() == 1) {
            fen = "0" + fen;
        }
        if (miao.length() == 1) {

        }
        String aa = "";
        if (!"00".equals(hour)) {
            aa = hour + MainApplication.getMainApplication().getString(R.string.hour)
                    + fen
                    + MainApplication.getMainApplication().getString(R.string.minute)
                    + miao
                    + MainApplication.getMainApplication().getString(R.string.second);
        } else if (!"00".equals(fen)) {
            aa = fen + MainApplication.getMainApplication().getString(R.string.minute)
                    + miao
                    + MainApplication.getMainApplication().getString(R.string.second);
        } else {
            aa = miao + MainApplication.getMainApplication().getString(R.string.second);
        }

        return aa;
    }

    public static double getDistance(double lat1, double lon1, double lat2,
                                     double lon2) {
        float[] results = new float[1];
        Location.distanceBetween(lat1, lon1, lat2, lon2, results);
        return results[0];
    }

    public static boolean hasSDCard() {
        String t = android.os.Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(t);
    }
}