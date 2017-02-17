<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library</title>
</head>
<body>
<h1>Oops ! something went wrong, we are trying to fix it <a href="#" onclick="history.go(-1);return false;" style="cursor:pointer">click here</a> to go back</h1>
Error message: <h3>${exception.errMsg}</h3>
</body>
</html>