package com.enuocms.account.admin;


import com.enuocms.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "admin")
public class Admin extends BaseEntity {
	// 用户姓名
	private String name;

	// 密码
	private String password;
}
