package com.changping.shiro.factory;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.changping.shiro.pojo.Filter_Chain_Definition;
import com.changping.shiro.service.UserServiceInf;
public class FilterChainDefinitionMapBuilder {
	@Autowired
	UserServiceInf userServiceInf;
	public LinkedHashMap<String,String> buildFilterChainDefinitionMap()
	{
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		List<Filter_Chain_Definition> filter_Chain_Definition_List = userServiceInf.get_filter_chain_definition_list();
		for(Filter_Chain_Definition fcd:filter_Chain_Definition_List)
		{
			String chain_Name =fcd.getChain_Name().toString();
			String chain_Definition = fcd.getChain_Definition().toString();
			map.put(chain_Name,chain_Definition);
		}
		return map;
	}
}
/*map.put("/login.jsp", "anon");
map.put("/shiro_login", "anon");
map.put("/add_user.jsp", "anon");
map.put("/add_user", "anon");
map.put("/shiro_logout", "logout");
map.put("/user_Unique.jsp", "roles[user]");
map.put("/admin_Unique.jsp", "roles[admin]");
map.put("/**", "authc");*/
