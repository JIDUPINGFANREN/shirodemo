package com.changping.shiro.dao.oracledao;
import com.changping.shiro.pojo.User;
//Spring中不需要这个注解：@Mapper
public interface UserMapper2 {
	public User select_user_by_username(String username);

	public int insert_user(User add_user); 
}
