package com.changping.shiro.service;
import java.util.List;

import com.changping.shiro.pojo.Filter_Chain_Definition;
import com.changping.shiro.pojo.User;
public interface UserServiceInf {
	public User find_user_by_username(String username);

	public int add_user(User add_user);

	public String testAnnotation();

	public List<Filter_Chain_Definition> get_filter_chain_definition_list();	
}
