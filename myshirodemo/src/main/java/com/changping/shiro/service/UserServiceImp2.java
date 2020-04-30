package com.changping.shiro.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changping.shiro.dao.oracledao.UserMapper2;
import com.changping.shiro.pojo.User;
@Service
public class UserServiceImp2 implements UserServiceInf2 {
	@Autowired
	UserMapper2 userMapper;
	@Override
	public User find_user_by_username(String username) {
		User user = userMapper.select_user_by_username(username);
		return user;
	}
	@Override
	public int add_user(User add_user) {
		//给字段db_source赋值，因为oracle好像不能mysql那样通过database()方法获得数据库名。
		add_user.setDb_source("db_oracle_0424");
		int insert_result = userMapper.insert_user(add_user);
		return insert_result;
	}
}
