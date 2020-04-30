<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>add_user_Page</h1>
		<br>
	    <br> 
	<form action="add_user" method="post">
		<table border="1px" width="400px" height="100px" align="center">
			<tr>
				<td><input type="text" name="userid" style="width:200px"/></td>
			<tr />
			<tr>
				<td><input type="text" name="username" style="width:200px"/></td>
			<tr />
			<tr>
				<td><input type="password" name="userpass" style="width:200px"/></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>