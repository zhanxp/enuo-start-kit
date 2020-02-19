package com.enuocms.boot.util;

import com.enuocms.boot.config.shiro.AccountAuthorizationRealm;
import org.apache.shiro.SecurityUtils;
import com.enuocms.boot.config.shiro.ShiroUser;

/**
 * Created by zhanxiaoping on 2017/8/30.
 * zhanxp@me.com
 */
public class UserUtil {
    public static ShiroUser getCurrentUser() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
