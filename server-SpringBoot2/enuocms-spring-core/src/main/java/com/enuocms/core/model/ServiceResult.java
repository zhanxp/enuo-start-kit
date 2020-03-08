package com.enuocms.core.model;

/**
 * Created by zhanxiaoping on 2020/3/7.
 * zhanxp@me.com
 */
public class ServiceResult {

    public ServiceResult(boolean result) {
        this(result, null);
    }

    public ServiceResult(Object data) {
        this(true,"OK",data);
    }

    public ServiceResult(boolean result, String message) {
        this(result, message, null);
    }

    public ServiceResult(boolean result, Object data) {
        this(result, null, data);
    }

    public ServiceResult(boolean result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ServiceResult(boolean result, ResultCode errorCode, String message,
                         Object data) {
        this.result = result;
        if(errorCode!= null){
            this.code = errorCode.getCode();
        }
        this.message = message;
        this.data = data;
    }

    private boolean result;
    private int code;
    private String message;
    private Object data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ServiceResult createFailureResult(String message) {
        return new ServiceResult(false, message);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code.getCode();
    }

    public enum ResultCode {
        NONE("OK", 200),
        NOT_LOGIN("您的登录状态已失效，请重新登录！", 401),
        SERVER_ERROR("网络出错，请重新操作！", 505),
        PERMISSION_DENIED("对不起，您没有执行该操作的权限", 403);

        private String display;
        private int code;

        // 构造方法
        private ResultCode(String display, int code) {
            this.display = display;
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

        public String getValue() {
            return name();
        }

        public static ResultCode fromCode(int code) {
            ResultCode[] states = ResultCode.values();
            for (ResultCode state : states) {
                if (state.getCode() == code) {
                    return state;
                }
            }
            return null;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.display;
        }

        public String getDisplay() {
            return display;
        }
    }
}
