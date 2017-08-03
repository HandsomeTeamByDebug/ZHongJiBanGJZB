<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>testShowUserById</title>
</head>
<body>
	<input type="hidden" value="${user.userId}"  name="userId"/>
	userName :<input type="text" value="${user.userName}" name="userName"/>
	phone :<input type="text" value="${user.phone}" name="phone"/>
	
</body>
</html>