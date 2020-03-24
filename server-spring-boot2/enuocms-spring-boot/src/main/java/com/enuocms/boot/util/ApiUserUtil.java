package com.enuocms.boot.util;

import com.enuocms.boot.vo.ApiUserVo;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */
public class ApiUserUtil {
    private static ThreadLocal<ApiUserVo> userInfo = new ThreadLocal<>();

    public static ApiUserVo getUserInfo() {
        return userInfo.get();
    }

    public static void setUserInfo(ApiUserVo user) {
        userInfo.set(user);
    }
}
