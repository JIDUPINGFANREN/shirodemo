<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>Shiro_List_Page</h1>
		Welcome:<shiro:principal></shiro:principal>
		
		<button onclick="add_user()">add_user</button>
		
		<shiro:hasRole name="user">
			<button onclick="goto_user_Unique()">goto_user_Unique</button>
		</shiro:hasRole>
		
		<shiro:hasRole name="admin">
	   		<button onclick="goto_admin_Unique()">goto_admin_Unique</button>
		</shiro:hasRole>
		
		<button onclick="only_admin_can_visit()">only_admin_can_visit</button>
		
</body>
<script type="text/javascript">
	function add_user() {
	  window.location.href="add_user.jsp";
	}
	function goto_user_Unique() {
	  window.location.href="user_Unique.jsp";
	}
	function goto_admin_Unique() {
	  window.location.href="admin_Unique.jsp";
	}
	function only_admin_can_visit() {
	  window.location.href="only_admin_can_visit";
	}

</script>	
</html>