package com.enuocms.web.shiro;

import com.enuocms.account.admin.AdminRole;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public  class ShiroUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    public int id;
    public String userName;
    public List<AdminRole> roles;

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

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
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