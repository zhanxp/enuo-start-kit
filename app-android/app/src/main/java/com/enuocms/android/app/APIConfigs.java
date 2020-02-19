package com.enuocms.android.app;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class APIConfigs {

    public  static final  String API_URL_ROOT = "http://192.168.9.140:8090/api/";
    public  static final  int API_DATA_PAGE_SIZE = 10;

    //网络响应数据解析key值
    public static final String API_RESPONSE_KEY_DATA = "data";
    public static final String API_RESPONSE_KEY_DATA_RESULT = "items";
    public static final String API_RESPONSE_KEY_PAGE_INDEX = "pageIndex";
    public static final String API_RESPONSE_KEY_PAGE_START_ID = "startID";
    public static final String API_RESPONSE_KEY_PAGE_SIZE = "pageSize";
    public static final String API_RESPONSE_KEY_PAGE_DATA_SIZE = "total";
    public static final String API_RESPONSE_KEY_PAGE_COUNT = "pageCount";

    public static final String API_REQUEST_KEY_PAGE_INDEX = "pageIndex";
    public static final String API_REQUEST_KEY_PAGE_SIZE = "pageSize";
    public static final String API_REQUEST_KEY_MULTI_BYTE = "multipart";
    public static final String API_REQUEST_KEY_DEVICE_ID = "deviceId";
    public static final String API_REQUEST_KEY_DEVICE_ID_AES = "key";
    public static final String API_REQUEST_KEY_FORMAT = "format";

    public static final String API_REQUEST_KEY_SIGN = "sign";
    public static final String API_REQUEST_KEY_TOKEN = "ticket";
    public static final String API_REQUEST_KEY_USER_ID = "userID";

    public static final String API_REQUEST_KEY_VERSION_CODE = "versionCode";
    public static final String API_REQUEST_KEY_CLIENT_TYPE = "clientType";
    public static final String API_REQUEST_KEY_TIME_SPAN = "timeSpan";

    public static final String API_REQUEST_KEY_UPLOAD_FILE = "file";
    public static final String API_REQUEST_KEY_UPLOAD_FILE_TYPE = "fileType";
}
