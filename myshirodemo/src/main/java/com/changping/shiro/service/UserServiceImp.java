package com.changping.shiro.service;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changping.shiro.controller.MySessionDAO;
import com.changping.shiro.dao.mysqldao.UserMapper;
import com.changping.shiro.pojo.Filter_Chain_Definition;
import com.changping.shiro.pojo.User;
@Service
public class UserServiceImp implements UserServiceInf {
	@Autowired
	UserMapper userMapper;
	@Override
	public User find_user_by_username(String username) {
		User user = userMapper.select_user_by_username(username);
		return user;
	}
	@Override
	public int add_user(User add_user) {
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
		int insert_result = userMapper.insert_user(add_user);
		return insert_result;
	}
	@RequiresRoles({"admin"})
	@Override
	public String testAnnotation() {
		String print = "annotation------>service";
		Session session = SecurityUtils.getSubject().getSession();
		Object value = session.getAttribute("一点了"); 	
		return print;
	}
	@Override
	public List<Filter_Chain_Definition> get_filter_chain_definition_list() {
		List<Filter_Chain_Definition> filter_chain_definition_List = userMapper.select_filter_chain_definition_list();
		return filter_chain_definition_List;
	}
}







