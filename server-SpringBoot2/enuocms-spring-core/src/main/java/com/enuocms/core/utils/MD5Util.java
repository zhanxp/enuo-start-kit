package com.enuocms.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanxiaoping on 2017/7/4.
 */
public  class MD5Util {
    /**
     *
     * 生成md5
     *
     * @param message
     * @return
     */
    public static String getMD5(String message) {
        String md5str = "";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2 将消息变成byte数组
            byte[] input = message.getBytes();

            // 3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);

            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }


    /**
     * md5加密方法 默认UTF-8编码
     *
     * @param sourceStr
     * @return
     */
    public static String getMD5Str(String sourceStr) {

        return getMD5Str(sourceStr, "UTF-8");
    }

    /**
     * md5加密方法
     *
     * @param sourceStr 需要加密的信息
     * @param charSet 试用的编码格式
     * @return
     */
    public static String getMD5Str(String sourceStr, String charSet) {
        if (sourceStr == null) {
            return null;
        }

        String result = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            try {

                md.update(sourceStr.getBytes(charSet));

            } catch (UnsupportedEncodingException e) {

                md.update(sourceStr.getBytes());

            }
            byte b[] = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 根据字母和数字32位组合判断是否被md5加密
     * @param sourceValue
     * @return
     */
    public static String getMD5StrNew(String sourceValue){
        if(StringUtils.isBlank(sourceValue)){
            return "";
        }

        Pattern pattern = Pattern.compile("[0-9a-zA-Z]{32}");
        Matcher matcher = pattern.matcher(sourceValue);
        if (matcher.find()) {
            return sourceValue;
        }
        return MD5Util.getMD5Str(sourceValue);
    }
}
