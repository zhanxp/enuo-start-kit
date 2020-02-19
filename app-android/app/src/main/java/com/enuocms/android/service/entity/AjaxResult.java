package com.enuocms.android.service.entity;

import com.enuocms.android.R;
import com.enuocms.android.app.Configs;
import com.enuocms.android.app.MainApplication;
import com.enuocms.android.utils.JSONHelper;
import com.enuocms.android.utils.StringHelper;
import com.enuocms.android.utils.SuperToastHelper;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class AjaxResult {

        //网络请求协议头
        public static final String URL_SCHEME = "enuocms";
        //执行结果
        public final static String RESULT_KEY = "st";
        public final static String RESULT_ERROR = "error";
        public final static String RESULT_SUCCESS = "success";
        //执行消息
        public final static String MSG_KEY = "msg";
        //错误代码
        public final static String ERROR_CODE_KEY = "code";

public class ErrorCode{
    public final static int NONE = 200;							//没有错误
    public final static int LOGIN_STATE_EXPIRY = 401;			//登录状态失效
    public final static int NOT_Valid = 402;					//安全验证失败
    public final static int PERMISS_DENIED = 403;				//没有权限
    public final static int JAVA_CODE_ERROR = 505;				//网络错误
    public final static int NOT_COMPANY = 4031;					//没有所属公司
    public final static int NOT_ALLOW_QUATE = 4032;				//不允许参与报价
}

    //请求连接情况，true - 连接正常，false - 连接失败，为true时，其他字段信息有效。
    public boolean connected;

    public boolean success;		//执行情况
    public int code;		//执行代码
    public String msg;		//附加消息
    public String title;		//附加标题

    public AjaxResult() {
    }

    /*
        * @function         解析AjaxResult
        * @object           服务端返回的JSONObject对象
        * @return           解析结果，非空
        * */
    public static AjaxResult parseAjaxResult(JSONObject object) {
        //1、解析AjaxResult
        AjaxResult ajaxResult = new AjaxResult();
        //1.1、解析AjaxResult附加信息
        AjaxResult ajaxResultCopy = JSONHelper.toObject(object, AjaxResult.class);
        if (ajaxResultCopy != null){
            ajaxResult = ajaxResultCopy;
        }
        //1.2、解析AjaxResult连接情况
        if (object != null) {
            ajaxResult.connected = true;
        } else {
            ajaxResult.connected = false;
        }
        return ajaxResult;
    }


    /*
    * @function         判断是否匹配scheme
    * @return           判断结果
    * */
    public static boolean isMatchScheme(String url){
        if (StringHelper.empty(url)){
            return false;
        }
        if (url.startsWith(URL_SCHEME)){
            return true;
        }
        return false;
    }

    /*
        * @function         解析AjaxResult
        * @object           服务端跳转的url
        * @return           解析结果，非空
        * */
    public static AjaxResult parseAjaxResult(String url) {
        //1、解析AjaxResult
        AjaxResult ajaxResult = new AjaxResult();
        //1.1、解析AjaxResult附加信息
        if (url.contains("?")){
            String splitList[] = url.split("[?]");
            if (splitList.length > 1){
                String splitParamList[] = splitList[1].split("&");
                for (int i = 0; i < splitParamList.length; i++){
                    String splitUnitParamList[] = splitParamList[i].split("=");
                    if (splitUnitParamList.length == 2){
                        if (RESULT_KEY.equals(splitUnitParamList[0])){
                            if (RESULT_SUCCESS.equals(splitUnitParamList[1])){
                                ajaxResult.success = true;
                            }else {
                                ajaxResult.success = false;
                            }
                        }else if (MSG_KEY.equals(splitUnitParamList[0])){
                            try {
                                ajaxResult.msg = URLDecoder.decode(splitUnitParamList[1],"UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }else if (ERROR_CODE_KEY.equals(splitUnitParamList[0])){
                            ajaxResult.code = Integer.parseInt(splitUnitParamList[1]);
                        }
                    }
                }
            }
        }
        //1.2、解析AjaxResult连接情况
        if (StringHelper.empty(url)) {
            ajaxResult.connected = false;
        } else {
            ajaxResult.connected = true;
        }
        return ajaxResult;
    }

    /*
    * @function         调试信息
    * @flag				附加标示
    * */
    public void debug(boolean flag){
        if (connected) {
            if (success) {
                if (flag){
                    debugResultSuccess();
                }
            }else{
                debugResultError();
            }
        }else{
            debugConnectedError();
        }
    }

    /*
    * @function    网络异常
    * @date        2015-9-1
    */
    public static void debugConnectedError() {
        String errorMsg = MainApplication.getMainApplication().getString(R.string.network_connected_error);
        SuperToastHelper.e(errorMsg);
    }

    /*
    * @function    结果异常
    * @date        2015-9-1
    */
    public void debugResultError() {
        if (Configs.FLAG_DEBUG){
            String warnMsg = MainApplication.getMainApplication().getString(R.string.network_result_error) + ":(msg=" + msg + ",code=" + code + ")";
            SuperToastHelper.w(warnMsg);
        }else{
            SuperToastHelper.w(msg);
        }
    }

    /*
    * @function    结果正常
    * @date        2015-9-1
    */
    public void debugResultSuccess() {
        if (StringHelper.empty(msg)){
            return;
        }
        SuperToastHelper.i(msg);
    }
}
