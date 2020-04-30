package com.changping.shiro.controller;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.changping.shiro.pojo.User;
import com.changping.shiro.service.UserServiceInf;
import com.changping.shiro.service.UserServiceInf2;
@Controller
public class ShiroController {
	@Autowired
	UserServiceInf userService_mysql;
	@Autowired
	UserServiceInf2 userService_oracle;
	//通过用户名、密码来获得认证的token，之后传入MyShiroRealm里，在那里完成密码的比对，相当于从控制层里解耦化。
	//the authentication token containing the user's principal and credentials.
	//token实现了序列化
	@RequestMapping("shiro_login")
	public String shiro_login(HttpSession session,@RequestParam("username") String username,@RequestParam("password") String password)
	{
	/*	//先在此处测试确保查询用户能查到，再把代码搬到MyShiroRealm.java里。
     	User db_user_msyql = userService_mysql.find_user_by_username(username);
		System.out.println(db_user_msyql.toString());
		//先在此处测试确保查询用户能查到，再把代码搬到MyShiroRealm2.java里。
		User db_user_oracle = userService_oracle.find_user_by_username(username);
		System.out.println(db_user_oracle.toString());  */
		
		//获得对象
		Subject currentUser = SecurityUtils.getSubject();
		//判断是否被认证
		  if (!currentUser.isAuthenticated()) {
			    /**
			     * 这里的token同时传入MyShiroRealm和MyShiroRealm2
			     **/
	            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	            session.setAttribute("token", token);
	            token.setRememberMe(true);	  
	            try {
	            	System.out.println("token_hash:"+token.hashCode());
	                currentUser.login(token);
	            } 
	            catch (AuthenticationException ae) {
	            	System.out.println("登陆失败..."+ae.getMessage());
	            }
	            }
		return "redirect:/list.jsp";
	}
	
	//msyql添加用户
	@RequestMapping("add_user")
	public String add_user(
	@RequestParam("userid") String userid,
	@RequestParam("username") String username,
	@RequestParam("userpass") String userpass){
		
		//mysql、oracle共用参数
		Object credentials = userpass;//加密对象是密码
		Object salt = ByteSource.Util.bytes(userid);//获得盐的计算结果
		int hashIterations = 5000;//加密次数

		//mysql密码加密通过MD5
		String hashAlgorithmName_msyql = "MD5";
		Object md5Pass = new SimpleHash(hashAlgorithmName_msyql, credentials, salt, hashIterations);
		userpass = md5Pass.toString();//Object转String
		User adduser = new User(userid,userpass,username);
		int addresult1 = userService_mysql.add_user(adduser);
		
		//oracle密码加密通过SHA1
		String hashAlgorithmName_oracle = "SHA1";
		Object SHA1Pass = new SimpleHash(hashAlgorithmName_oracle, credentials, salt, hashIterations);
		userpass = SHA1Pass.toString();//Object转String
		User adduser2 = new User(userid,userpass,username);
		int addresult2= userService_oracle.add_user(adduser2);
		return "redirect:/list.jsp";
	}

	@RequestMapping("only_admin_can_visit")
	public String only_admin_can_visit(HttpSession session)
	{
		session.setAttribute("一点了", "还早...");
		String annotation = userService_mysql.testAnnotation();
		
		System.out.println("only_admin_can_visit<-----test----->");
		System.out.println(annotation);
		return "redirect:/list.jsp";
		
	}
}