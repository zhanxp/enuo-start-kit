package com.enuocms.web.shiro;

import com.enuocms.account.admin.Admin;
import com.enuocms.account.admin.AdminRole;
import com.enuocms.account.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Slf4j
public class AccountAuthorizationRealm extends AuthorizingRealm {

	@Resource
	private AdminService adminService;
	private String name = "AccountAuthorizationRealm";

	public String getName() {
		return name;
	}

	/**
	 * 查询获得用户信息 AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
	 *
	 * AuthenticationInfo有两个作用： 1、如果Realm 是AuthenticatingRealm
	 * 子类，则提供给AuthenticatingRealm 内部使用的
	 * CredentialsMatcher进行凭据验证；（如果没有继承它需要在自己的Realm中自己实现验证）；
	 * 2、提供给SecurityManager来创建Subject（提供身份信息）；
	 *
	 * @param authcToken
	 * @return
	 * @throws org.apache.shiro.authc.AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Admin member = adminService.getByName(token.getUsername());
		if (member == null) {
			throw new UnknownAccountException();// 用户不存在
		} else {
            String hashAlgorithmName = "MD5";
            int hashIterations = 1;
            //todo 数据库里没有加密
            Object credentials = new SimpleHash(hashAlgorithmName,  member.getPassword(), null, hashIterations);
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
					new ShiroUser(member.getId(),member.getName()),
                    credentials,
					getName()
			);
			return info;
		}
	}

	/**
	 * 表示根据用户身份获取授权信息 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole(AdminRole.NONE_ROLE.name());
		for (AdminRole role : shiroUser.getRoles()) {
			info.addRole(role.name());
		}
		return info;
	}

}
