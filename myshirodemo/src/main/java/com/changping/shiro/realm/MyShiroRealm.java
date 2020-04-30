package com.changping.shiro.realm;
import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.changping.shiro.pojo.User;
import com.changping.shiro.service.UserServiceInf;

public class MyShiroRealm extends AuthorizingRealm {//替换原来的AuthenticatingRealm
	@Autowired
	UserServiceInf userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("代码已经来到了：MyShiroRealm授权区域1----->");
		// 1.把AuthenticationToken 转换为UsernamePasswordToken
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		// 2.从UsernamePasswordToken中获取username
		String shiro_Username = usernamePasswordToken.getUsername();
		// 3.调用数据库方法，从数据库中查询username对应的用户记录
		User db_user_mysql = userService.find_user_by_username(shiro_Username);
		// 4.若用户不存在，则可以抛出UnknowAccountException异常
		if (db_user_mysql == null) {
			throw new UnknownAccountException("用户不存在!");
		}
		// 5.根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常。
		if ("002".equals(db_user_mysql.getUserid())) {
			System.out.println("LockedAccountException:" + db_user_mysql.getUserid());
			throw new LockedAccountException("用户已被锁定!");
		} else {
			System.out.println("通过realm-----没有异常!");
		}
		/*
		 * 6.根据用户的情况，来构建AuthenticationInfo对象并返回,通常使用的方法为：
		 * SimpleAuthenticationInfo以下信息是从数据库中获取：
		 */
		// 1、认证的实体信息，可以是username，也可以是数据表对应的用户的实体类对象User。
		// 2、密码
		// 3、盐的计算结果，使用接口ByteSource的内部类Util里的bytes方法。所以说接口里可以有内部类，这个内部类是个final类。
		// 4、当前realm对象的name,调用父类的getName()方法即可。

		Object principal =db_user_mysql.getUsername();
		Object hashedCredentials = db_user_mysql.getUserpass();
		ByteSource credentialsSalt = ByteSource.Util.bytes(db_user_mysql.getUserid());// 用数据库中userid作为盐。
		String realmName = getName();

		SimpleAuthenticationInfo info = null; /* new SimpleAuthenticationInfo(principal, credentials, realmName); */
		info = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		return info;
	}

	/**
	 * 授权时，可以继承AuthorizingRealm抽象类
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("访问了需要授权的页面，开始授权......");
		// 1.从PrincipalCollection中来获取登陆用户的用户名
		Object principal = principals.getPrimaryPrincipal();
		// 2.利登陆的用户的信息来获取当前用户的角色或权限(需要查询数据库)
		User db_user_mysql = userService.find_user_by_username((String)principal);
		Set<String> role = new HashSet<String>();
		role.add("user");
		if("admin".equals(db_user_mysql.getRole()))
		{
			role.add("admin");
		}
		// 3.创建SimpleAuthorizationInfo,并设置其realms属性
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(role);
		// 4.返回SimpleAuthorizationInfo对象
		return info;
	}
}
