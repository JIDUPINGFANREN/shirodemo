package com.changping.shiro.realm;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.changping.shiro.pojo.User;
import com.changping.shiro.service.UserServiceInf2;

public class MyShiroRealm2 extends AuthenticatingRealm{
	@Autowired
	UserServiceInf2 userService;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("代码已经来到了：MyShiroRealm授权区域2----->");
		// 1.把AuthenticationToken 转换为UsernamePasswordToken
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
		// 2.从UsernamePasswordToken中获取username
		String shiro_Username = usernamePasswordToken.getUsername();
		// 3.调用数据库方法，从数据库中查询username对应的用户记录
		User db_user_oracle = userService.find_user_by_username(shiro_Username);
		// 4.若用户不存在，则可以抛出UnknowAccountException异常
		if(db_user_oracle==null) 
		{
			throw new UnknownAccountException("用户不存在!");
		}
		// 5.根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常。
		if("002".equals(db_user_oracle.getUserid()))
		{
			System.out.println("LockedAccountException:"+db_user_oracle.getUserid());
			throw new LockedAccountException("用户已被锁定!");
		}
		else {
			System.out.println("通过realm-----没有异常!");
		}
		/*6.根据用户的情况，来构建AuthenticationInfo对象并返回,通常使用的方法为：SimpleAuthenticationInfo以下信息是从数据库中获取：*/
		//1、认证的实体信息，可以是username，也可以是数据表对应的用户的实体类对象User。
		//2、密码
		//3、盐的计算结果，使用接口ByteSource的内部类Util里的bytes方法。所以说接口里可以有内部类，这个内部类是个final类。
		//4、当前realm对象的name,调用父类的getName()方法即可。
		
		Object principal = db_user_oracle.getUsername();
		Object hashedCredentials = db_user_oracle.getUserpass();
		ByteSource credentialsSalt = ByteSource.Util.bytes(db_user_oracle.getUserid());//用数据库中userid作为盐。
		String realmName = getName();
		
		SimpleAuthenticationInfo info =null; /* new SimpleAuthenticationInfo(principal, credentials, realmName);*/
		info = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		return info;
	}
}
