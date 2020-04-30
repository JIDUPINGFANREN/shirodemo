package com.changping.shiro.dao.mysqldao;
import java.util.List;

import com.changping.shiro.pojo.Filter_Chain_Definition;
import com.changping.shiro.pojo.User;
//Spring中不需要这个注解：@Mapper
public interface UserMapper {
	public User select_user_by_username(String username);

	public int insert_user(User add_user);

	public List<Filter_Chain_Definition> select_filter_chain_definition_list(); 
}
