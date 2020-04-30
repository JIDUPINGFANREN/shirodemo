package com.changping.shiro.service;
import com.changping.shiro.pojo.User;
public interface UserServiceInf2 {
	public User find_user_by_username(String username);

	public int add_user(User add_user);	
}
