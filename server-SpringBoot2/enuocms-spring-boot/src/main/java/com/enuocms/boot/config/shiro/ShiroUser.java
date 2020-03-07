package com.enuocms.boot.config.shiro;

/**
 * Created by zhanxiaoping on 2017/8/30.
 * zhanxp@me.com
 */

import com.enuocms.account.model.ADMIN_TYPES;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public  class ShiroUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    public int id;
    public String userName;
    public List<ADMIN_TYPES> roles;

    public ShiroUser(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return userName;
    }

    public List<ADMIN_TYPES> getRoles() {
        return roles;
    }

    public void setRoles(List<ADMIN_TYPES> roles) {
        this.roles = roles;
    }

    /**
     * 重载equals,只计算name;
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "userId");
    }

    /**
     * 重载equals,只比较loginName
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "userId");
    }
}