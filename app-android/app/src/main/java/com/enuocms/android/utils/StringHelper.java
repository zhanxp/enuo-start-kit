package com.enuocms.android.utils;

import com.enuocms.android.R;
import com.enuocms.android.app.MainApplication;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class StringHelper {
    public final static String STR_HEX = "0123456789ABCDEF";

    /*
    * @function		将字符串转换为整形
    * */
    public static Integer toInteger(String strInteger) {
        if (empty(strInteger)){
            return 0;
        }else{
            return Integer.parseInt(strInteger);
        }
    }

    /*
    * @function		将字符串转换为浮点数
    * */
    public static Double toDouble(String strDouble) {
        Double strResult = 0.0;

        if (!empty(strDouble)){
            try {
                strResult = Double.parseDouble(strDouble);
            } catch (NumberFormatException e) {

            }
        }

        return strResult;
    }

    /*
    * @function		将字节数组转换为十六进制字符串
    * */
    public static String fromByteArrayToHex(byte[] byteArray) {
        StringBuilder strHex = new StringBuilder();
        for (byte b : byteArray) {
            int i = 0xFF & b;
            if (i < 0x10) {
                strHex.append("0" + Integer.toHexString(i));
            } else {
                strHex.append(Integer.toHexString(i));
            }
        }
        return strHex.toString();
    }

    /*
    * @function		将字节数组转换为字符串
    * */
    public static String fromByteArray(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            sb.append(byteArray[i]);
        }
        return sb.toString();
    }

    /*
    * @function		转换为md5字符串
    * */
    public final static String toMD5(String plainText) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = plainText.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /*
    * @function		判断字符串是否为空串
    * */
    public static boolean empty(String val) {
        return val == null || val.trim().length() <= 0;
    }

    /*
    * @function		如果对象为null，则返回空串，否则，返回本身
    * */
    public static String fill(String val) {
        if (val == null){
            return "";
        }else{
            return val;
        }
    }

    /*
    * @function		如果对象为null，则返回0，否则，返回本身
    * */
    public static String fill(Double val) {
        if (val == null){
            return "0";
        }else{
            return val + "";
        }
    }

    /*
    * @function		如果对象为null，则返回0，否则，返回本身
    * */
    public static String fill(Integer val) {
        if (val == null){
            return "0";
        }else{
            return val + "";
        }
    }

    /*
    * @function		按照约定格式化字符串，将日期字符串格式化为日期
    * @strDate		日期字符串
    * @return		日期
    * */
    public static Date toDefaultFormatDate(String strDate) {
        return toFormatDate(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    /*
    * @function		指定格式化字符串，将日期字符串格式化为日期
    * @strDate		日期字符串
    * @strFormat	格式化字符串
    * @return		日期
    * */
    public static Date toFormatDate(String strDate, String strFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /*
    * @function		将字符串格式化为长整型
    * */
    public static Long toLong(String strLong){
        Long rLong = 0l;
        if (!empty(strLong)){
            rLong = Long.parseLong(strLong);
        }
        return rLong;
    }

    /*
    * @function		制定格式化字符串，将日期格式化为日期字符串
    * @date			日期
    * @strFormat	格式化字符串
    * @return		日期字符串
    * */
    public static String fromFormatDate(Date date, String strFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        return sdf.format(date);
    }

    /*
    * @function		按照约定格式化字符串，将日期格式化为日期字符串
    * @date			日期
    * @return		日期字符串
    * */
    public static String fromDefaultFormatDate(Date input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(input);
    }

    /*
    * @function		转换为标准日期格式化字符串
    * @date			日期
    * @return		标准日期字符串
    * */
    public static String toStandardDate(Date date) {
        long timeStr = date.getTime(); // Long.parseLong(timeStr);
        StringBuffer sb = new StringBuffer();
        long time = System.currentTimeMillis() - timeStr;
        long mill = (long) Math.ceil(time / 1000);
        long minute = (long) Math.ceil(time / 60 / 1000.0f);
        long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);
        long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);
        if (day - 1 > 0) {
            if (day < 7) {
                sb.append(day
                        + CommonHelper.getResourceString(R.string.days_ago));
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sb.append(sdf.format(date));
            }
        } else if (hour - 1 > 0) {

            sb.append(hour
                    + CommonHelper.getResourceString(R.string.hours_ago));

        } else if (minute - 1 > 0) {

            sb.append(minute
                    + CommonHelper.getResourceString(R.string.minutes_ago));

        } else if (mill - 1 > 0) {

            sb.append(MainApplication.getMainApplication().getString(R.string.just_now));

        }
        return sb.toString();
    }

    /*
    * @function		是否全部由字符或空格组成
    * */
    public static boolean isAllLetter(String str) {
        Pattern pattern = Pattern.compile("[a-z A-Z]*");
        return pattern.matcher(str).matches();
    }

    public static String encrypt(String seed, String cleartext)
            throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] result = encrypt(rawKey, cleartext.getBytes());
        return toHex(result);
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    public static String decrypt(String seed, String encrypted)
            throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted)
            throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    /*
    * @function		计算原始字节数组的key值
    * */
    public static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(STR_HEX.charAt((b >> 4) & 0x0f)).append(STR_HEX.charAt(b & 0x0f));
    }

    public static String getString(String str, int len) {
        int strLen = str.length();
        if (strLen > len) {
            return str.substring(0, len) + "...";
        }
        return str;
    }

    public static String subString(String text, int length, String endWith) {
        int textLength = text.length();
        int byteLength = 0;
        StringBuffer returnStr = new StringBuffer();
        for (int i = 0; i < textLength && byteLength < length * 2; i++) {
            String str_i = text.substring(i, i + 1);
            if (str_i.getBytes().length == 1) {
                byteLength++;
            } else {
                byteLength += 2;
            }
            returnStr.append(str_i);
        }
        try {
            if (byteLength < text.getBytes("GBK").length) {
                returnStr.append(endWith);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnStr.toString();
    }
}