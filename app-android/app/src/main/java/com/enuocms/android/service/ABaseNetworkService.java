package com.enuocms.android.service;

import android.app.Activity;

import com.androidquery.AQuery;
import com.enuocms.android.app.APIConfigs;
import com.enuocms.android.app.Configs;
import com.enuocms.android.app.MainApplication;
import com.enuocms.android.entity.UploadResult;
import com.enuocms.android.entity.UserInfo;
import com.enuocms.android.service.callback.AjaxDataCallback;
import com.enuocms.android.service.callback.AjaxListDataCallback;
import com.enuocms.android.service.callback.AjaxObjectDataCallback;
import com.enuocms.android.service.callback.AjaxPageDataCallback;
import com.enuocms.android.service.handler.AjaxDataHandler;
import com.enuocms.android.service.handler.AjaxListDataHandler;
import com.enuocms.android.service.handler.AjaxObjectDataHandler;
import com.enuocms.android.service.handler.AjaxPageDataHandler;
import com.enuocms.android.utils.AesHelper;
import com.enuocms.android.utils.Base64Helper;
import com.enuocms.android.utils.DateHelper;
import com.enuocms.android.utils.StringHelper;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class ABaseNetworkService implements ICommonService {

    //aquery封装对象
    protected AQuery aq;

    public ABaseNetworkService() {
        this.aq = MainApplication.getMainAQuery();
    }

    public ABaseNetworkService(Activity activity) {
        this.aq = new AQuery(activity);
    }


    protected <T> void submitAjaxGetRequest(String url, final Map<String, Object> params, final AjaxDataHandler callbackHandler){
        url = appendAjaxGetParamsWithSign(url, params);
        this.aq.ajax(url, JSONObject.class, new AjaxDataCallback(callbackHandler));
    }

    protected <T> void submitAjaxPostRequest(String url, final Map<String, Object> params, final AjaxDataHandler callbackHandler){
        appendAjaxPostParams(params);
        this.aq.ajax(url, JSONObject.class, new AjaxDataCallback(callbackHandler));
    }


    /*
    * @function         提交分页数据GET请求
    * @url              请求地址
    * @params           请求参数
    * @callbackHandler  回调函数
    * @clazz            数据类型.class
    * */
    protected <T> void submitAjaxPageDataGetRequest(String url, final Map<String, Object> params, final AjaxPageDataHandler<T> callbackHandler, final Class<T> clazz){
        url = appendAjaxGetParamsWithSign(url, params);
        this.aq.ajax(url, JSONObject.class, new AjaxPageDataCallback<T>(callbackHandler, clazz));
    }

    /*
    * @function         提交分页数据POST请求
    * @url              请求地址
    * @params           请求参数
    * @callbackHandler  回调函数
    * @clazz            数据类型.class
    * */
    protected <T> void submitAjaxPageDataPostRequest(final String url, final Map<String, Object> params, final AjaxPageDataHandler<T> callbackHandler, final Class<T> clazz){
        appendAjaxPostParams(params);
        this.aq.ajax(url, params, JSONObject.class, new AjaxPageDataCallback<T>(callbackHandler, clazz));
    }

//    /*
//    * @function         提交反向分页数据GET请求
//    * @url              请求地址
//    * @params           请求参数
//    * @callbackHandler  回调函数
//    * @clazz            数据类型.class
//    * */
//    protected <T> void submitAjaxInvertedPageDataGetRequest(String url, final Map<String, Object> params, final AjaxInvertedPageDataHandler<T> callbackHandler, final Class<T> clazz){
//        url = appendAjaxGetParamsWithSign(url, params);
//        this.aq.ajax(url, JSONObject.class, new AjaxInvertedPageDataCallback<T>(callbackHandler, clazz));
//    }

//    /*
//    * @function         提交反向分页数据POST请求
//    * @url              请求地址
//    * @params           请求参数
//    * @callbackHandler  回调函数
//    * @clazz            数据类型.class
//    * */
//    protected <T> void submitAjaxInvertedPageDataPostRequest(final String url, final Map<String, Object> params, final AjaxInvertedPageDataHandler<T> callbackHandler, final Class<T> clazz){
//        appendAjaxPostParams(params);
//        this.aq.ajax(url, params, JSONObject.class, new AjaxInvertedPageDataCallback<T>(callbackHandler, clazz));
//    }

    /*
    * @function         提交列表数据GET请求
    * @url              请求地址
    * @params           请求参数
    * @callbackHandler  回调函数
    * @clazz            数据类型.class
    * */
    protected <T> void submitAjaxListDataGetRequest(String url, final Map<String, Object> params, final AjaxListDataHandler<T> callbackHandler, final Class<T> clazz){
        url = appendAjaxGetParamsWithSign(url, params);
        this.aq.ajax(url, JSONObject.class, new AjaxListDataCallback<T>(callbackHandler, clazz));
    }

    /*
    * @function         提交列表数据POST请求
    * @url              请求地址
    * @params           请求参数
    * @callbackHandler  回调函数
    * @clazz            数据类型.class
    * */
    protected <T> void submitAjaxListDataPostRequest(final String url, final Map<String, Object> params, final AjaxListDataHandler<T> callbackHandler, final Class<T> clazz){
        appendAjaxPostParams(params);
        this.aq.ajax(url, params, JSONObject.class, new AjaxListDataCallback<T>(callbackHandler, clazz));
    }

    /*
    * @function         提交列表数据GET请求
    * @url              请求地址
    * @params           请求参数
    * @callbackHandler  回调函数
    * @clazz            数据类型.class
    * */
    protected <T> void submitAjaxObjectDataGetRequest(String url, final Map<String, Object> params, final AjaxObjectDataHandler<T> callbackHandler, final Class<T> clazz){
        url = appendAjaxGetParamsWithSign(url, params);
        this.aq.ajax(url, JSONObject.class, new AjaxObjectDataCallback<T>(callbackHandler, clazz));
    }

    /*
    * @function         提交列表数据POST请求
    * @url              请求地址
    * @params           请求参数
    * @callbackHandler  回调函数
    * @clazz            数据类型.class
    * */
    protected <T> void submitAjaxObjectDataPostRequest(final String url, final Map<String, Object> params, final AjaxObjectDataHandler<T> callbackHandler, final Class<T> clazz){
        appendAjaxPostParams(params);
        this.aq.ajax(url, params, JSONObject.class, new AjaxObjectDataCallback<T>(callbackHandler, clazz));
    }

    /*
    * @function         创建分页请求参数对象，自动加入公共参数，比如，token等，以及传入的分页参数
    * */
    protected Map<String,Object> getAjaxPageParams(int pageIndex,int pageSize){
        Map<String,Object> commonParams = getAjaxParams();

        commonParams.put(APIConfigs.API_REQUEST_KEY_PAGE_INDEX, pageIndex);
        commonParams.put(APIConfigs.API_REQUEST_KEY_PAGE_SIZE, pageSize);

        return commonParams;
    }

//    /*
//    * @function         创建反向分页请求参数对象，自动加入公共参数，比如，token等，以及传入的分页参数
//    * */
//    protected Map<String,Object> getAjaxInvertedPageParams(long startID,int pageSize){
//        Map<String,Object> commonParams = getAjaxParams();
//
//        if (startID != 0){
//            commonParams.put(APIConfigs.API_REQUEST_KEY_PAGE_START_ID, startID);
//        }
//        commonParams.put(APIConfigs.API_REQUEST_KEY_PAGE_SIZE,pageSize);
//
//        return commonParams;
//    }

    /*
    * @function         创建多媒体请求参数对象，自动加入公共参数，比如，token等，
    * */
    protected Map<String,Object> getAjaxMultiParams(){
        Map<String,Object> commonParams = getAjaxParams();

        byte[] buffer = new byte[0];
        commonParams.put(APIConfigs.API_REQUEST_KEY_MULTI_BYTE, buffer);

        return commonParams;
    }

    /*
    * @function         创建带唯一标示（设备号）请求参数对象，自动加入公共参数，比如，token等，
    * */
    protected Map<String,Object> getAjaxUniqueParams(){
        Map<String,Object> commonParams = getAjaxParams();

        String deviceId = MainApplication.getMainApplication().getDeviceId();
        commonParams.put(APIConfigs.API_REQUEST_KEY_DEVICE_ID,deviceId);                //设备号

        return commonParams;
    }

    /*
    * @function         创建带唯一标示（设备号 -(AES)-> key）请求参数对象，自动加入公共参数，比如，token等，
    * */
    protected Map<String,Object> getAjaxUniqueAESParams(){
        Map<String,Object> commonParams = getAjaxParams();

        String deviceId = MainApplication.getMainApplication().getDeviceId();
        commonParams.put(APIConfigs.API_REQUEST_KEY_DEVICE_ID_AES, AesHelper.encode(deviceId));                //设备号 key

        return commonParams;
    }

    /*
    * @function         创建带format请求参数对象，自动加入公共参数，比如，token等，
    * */
    protected Map<String,Object> getAjaxUniqueFormatParams(){
        Map<String,Object> commonParams = getAjaxParams();

        commonParams.put(APIConfigs.API_REQUEST_KEY_FORMAT,"html");

        return commonParams;
    }

    /*
    * @function         创建请求参数对象，自动加入公共参数，比如，token等
    * */
    protected Map<String,Object> getAjaxParams(){
        Map<String,Object> commonParams = new HashMap<String,Object>();

        UserInfo userInfo = UserInfo.getInstance();
        if (userInfo.hasLogin()){
            commonParams.put(APIConfigs.API_REQUEST_KEY_TOKEN,userInfo.ticket);              //用户token
            commonParams.put(APIConfigs.API_REQUEST_KEY_USER_ID,userInfo.id);               //用户编号
        }

        int versionCode = MainApplication.getMainApplication().getVersionCode();
        commonParams.put(APIConfigs.API_REQUEST_KEY_VERSION_CODE,versionCode);              //版本号

        String clientType =  Configs.clientType;                                               //客户端类型
        commonParams.put(APIConfigs.API_REQUEST_KEY_CLIENT_TYPE,clientType);

        String timeSpan = DateHelper.getNow();                                              //时间间隔
        commonParams.put(APIConfigs.API_REQUEST_KEY_TIME_SPAN,timeSpan);

        return commonParams;
    }

    /*
    * @function         拼接url，追加参数列表，不带签名，GET请求
    * @url              原始url（不带参数）
    * @params           请求参数
    * @return           拼接之后的url
    * */
    protected String appendAjaxGetParams(String url, Map<String, Object> params){
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String,Object>> entrySet = params.entrySet();
        if (entrySet.size() > 0){       //添加"?"
            builder.append("?");
        }
        for (Map.Entry<String,Object> entry : entrySet) {
            builder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        if (entrySet.size() > 0){       //移除"&"
            builder.deleteCharAt(builder.length() - 1);
        }
        return url + builder.toString();
    }

    /*
    * @function         拼接url，追加参数列表，并签名，GET请求
    * @url              原始url（不带参数）
    * @params           请求参数
    * @return           拼接之后的url
    * */
    protected String appendAjaxGetParamsWithSign(String url, Map<String, Object> params){
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String,Object>> entrySet = params.entrySet();
        if (entrySet.size() > 0){       //添加"?"
            builder.append("?");
        }
        for (Map.Entry<String,Object> entry : entrySet) {
            builder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        //计算sign参数
        String sign = calSignParams(params);
        //拼接sign参数
        builder.append(APIConfigs.API_REQUEST_KEY_SIGN + "=" + sign);
        return url + builder.toString();
    }

    /*
    * @function         追加参数列表，POST请求
    * @params           请求参数
    * */
    protected void appendAjaxPostParams(Map<String, Object> params){
        //计算sign参数
        String sign = calSignParams(params);
        //拼接sign参数
        params.put(APIConfigs.API_REQUEST_KEY_SIGN,sign);
    }

    /*
    * @function         计算签名
    * @params           请求参数
    * @return           签名
    * */
    protected String calSignParams(Map<String, Object> params){
        String sign = "";

        //根据参数升序
        List<Map.Entry<String,Object>> entryList = new ArrayList<>(params.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> stringObjectEntry, Map.Entry<String, Object> t1) {
                return stringObjectEntry.getKey().compareTo(t1.getKey());
            }
        });

        //参数升序后字符串
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String,Object> entry : entryList) {
            builder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }

        //设备编号
        String deviceId = MainApplication.getMainApplication().getDeviceId();

        //算法处理
        sign = Base64Helper.encode(StringHelper.toMD5(builder.toString() + deviceId).getBytes());

        return sign;
    }

    /*
        * @function     上传文件
        * @imageFile    文件对象
        * @fileBelongs  文件类别
        * */
    public void uploadFile(File file, int fileBelongs, final AjaxListDataHandler<UploadResult> callbackHandler){
        //1、解析url
        String url = APIConfigs.API_URL_ROOT + "/base/fileUpload";
        //2、填充params
        Map<String,Object> params = getAjaxUniqueParams();
        params.put(APIConfigs.API_REQUEST_KEY_UPLOAD_FILE, file);
        params.put(APIConfigs.API_REQUEST_KEY_UPLOAD_FILE_TYPE, fileBelongs);
        //3、提交网络请求
        submitAjaxListDataPostRequest(url, params, callbackHandler, UploadResult.class);
    }

}