package com.enuocms.account.model;

import com.enuocms.core.model.EnumDataType;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public enum ADMIN_TYPES implements EnumDataType {
    NONE_ROLE("无", 0),
    ACCOUNT_MANAGER("账户管理员", 1);

    private String display;
    private int code;

    // 构造方法
    private ADMIN_TYPES(String display, int code) {
        this.display = display;
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public String getValue(){
        return name();
    }

    public static ADMIN_TYPES fromCode(int code){
        ADMIN_TYPES[] states = ADMIN_TYPES.values();
        for(ADMIN_TYPES state: states) {
            if(state.getCode()==code) {
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
