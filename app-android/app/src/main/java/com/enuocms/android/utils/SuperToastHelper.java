package com.enuocms.android.utils;

import com.enuocms.android.app.MainApplication;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class SuperToastHelper {

    //---------------Info------------------//
    public static void i(String info) {
        SuperToast.create(MainApplication.getMainApplication(), info, SuperToast.Duration.VERY_SHORT,
                Style.getStyle(Style.GREEN, SuperToast.Animations.POPUP)).show();
    }

    //---------------Warning------------------//
    public static void w(String warn) {
        SuperToast.create(MainApplication.getMainApplication(), warn, SuperToast.Duration.VERY_SHORT,
                Style.getStyle(Style.BLUE, SuperToast.Animations.POPUP)).show();
    }

    //---------------Error------------------//
    public static void e(String error) {
        SuperToast.create(MainApplication.getMainApplication(), error, SuperToast.Duration.VERY_SHORT,
                Style.getStyle(Style.RED, SuperToast.Animations.POPUP)).show();
    }
}
