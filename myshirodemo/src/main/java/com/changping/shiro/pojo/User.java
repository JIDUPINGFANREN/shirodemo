package com.changping.shiro.pojo;
public class User {
	private String userid;
	private String userpass;
	private String username;
	private String db_source;
	private String role;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDb_source() {
		return db_source;
	}
	public void setDb_source(String db_source) {
		this.db_source = db_source;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	public User(String userid, String userpass, String username, String db_source,String role) {
		super();
		this.userid = userid;
		this.userpass = userpass;
		this.username = username;
		this.db_source = db_source;
		this.role = role;
	}
	//三参构造器，用于增加用户
	public User(String userid, String userpass, String username) {
		super();
		this.userid = userid;
		this.userpass = userpass;
		this.username = username;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", userpass=" + userpass + ", username=" + username + ", db_source="
				+ db_source + ", role=" + role + "]";
	}
}

