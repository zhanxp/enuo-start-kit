package com.enuocms.android.utils;
import java.util.Date;
/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class DateHelper {
    /*
    * @function     获取当前系统时间
    * */
    public static String getNow() {
        String nowTime = "";
        Date date = new Date();
        nowTime = StringHelper.fromDefaultFormatDate(date);
        return nowTime;
    }
}
