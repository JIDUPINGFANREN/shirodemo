package com.changping.shiro.controller;
import java.io.Serializable;
import java.util.List;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.changping.shiro.util.SerializableUtils;

public class MySessionDAO extends EnterpriseCacheSessionDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping("doCreate")
	//向数据库添加一个session
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);// 分配
		String sql = "insert into sessions (id,session) values (?,?)";
		// 把id和被序列化后的sessioin存入数据库
		jdbcTemplate.update(sql, sessionId, SerializableUtils.serialize(session));//给参数赋值
		return session.getId();
	}
	
	//条件查询session
	protected Session doReadSession(Serializable sessionId) {
		String sql = "select session from sessions where id=?";
		List<String> sessionStrList = jdbcTemplate.queryForList(sql, String.class, sessionId);
		if (sessionStrList.size() == 0)
			return null;
		return SerializableUtils.deserialize(sessionStrList.get(0));
	}
	
    protected void doUpdate(Session session) {
    	if(session instanceof ValidatingSession&&!((ValidatingSession) session).isValid())
    		return;
    	String sql ="update sessions set session=? where id=?";
    	jdbcTemplate.update(sql,SerializableUtils.serialize(session),session.getId());
    }

    protected void doDelete(Session session) {
    	String sql ="delete from sessions where id=?";
    	jdbcTemplate.update(sql,session.getId());
    }
}
