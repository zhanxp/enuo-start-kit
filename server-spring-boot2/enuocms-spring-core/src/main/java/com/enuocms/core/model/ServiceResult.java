package com.enuocms.core.model;

import lombok.Data;

/**
 * Created by zhanxiaoping on 2020/3/7.
 * zhanxp@me.com
 */
@Data
public class ServiceResult<T> {

    public ServiceResult(boolean success) {
        this(200, null,null);
    }

    public ServiceResult(T data) {
        this(ResultCode.NONE,data);
    }


    public ServiceResult(ResultCode code,T data) {
        this(code.getCode(),code.getDisplay(),data);
    }

    public ServiceResult(Integer code, String message,T data) {
        this.success = code == 200;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private boolean success;
    private int code;
    private String message;
    private T data;

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
