package com.changping.shiro.realm;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.changping.shiro.pojo.User;
public class AuthorizingRealm_Sub extends AuthorizingRealm{
	/**
	 * 该方法用于授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
	/**
	 * 该方法用于认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		return null;
	}
public static void main(String[] args) {
	User add_user = new User();
	//声明随机数[1,3)，如果是1就是admin，如果是2就user。
	int a = 1;
	int b = 3;
	int num = (int)(Math.random()*(b-a))+a;
	String random_role = null;
	if(num == 1)
	{
		random_role = "admin";
	}
	else
	{
		random_role = "user";
	}
	add_user.setRole(random_role);
}
}
