package com.enuocms.android.utils;

import android.util.Base64;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class Base64Helper {

    /*
    * @function     加密算法
    * */
    public static String encode( byte[] plainText){
        String encodeText = new String(Base64.encode(plainText,Base64.NO_WRAP));
        encodeText = encodeText.replace("+","-");
        encodeText = encodeText.replace("/","_");
        return encodeText;
    }

    /*
    * @function     解密算法
    * */
    public static byte[] decode( String encodeText){
        encodeText = encodeText.replace("-","+");
        encodeText = encodeText.replace("_","/");
        return Base64.decode(encodeText,Base64.NO_WRAP);
    }

}
