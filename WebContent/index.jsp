<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia!</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${context}/css/index.css">
<link type="text/css" rel="stylesheet" href="${context}/css/style.css">

<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="${context}/images/favicon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="${context}/images/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="${context}/images/favicon/favicon-16x16.png">
<link rel="manifest" href="${context}/images/favicon/site.webmanifest">
</head>
<body>


<div class="index-bg">
<div class="middle">

<h1>Boku No Pet Acadamia: Budokai Tenkaichi 3 Ultra Edition!</h1>

<c:if test="${LOGIN_FAILED}">
	<p style="color: red;">Incorrect username and/or password!</p>
</c:if>

<form action="LoginControllerServlet" method="POST" id="login-form">
	<input type="hidden" name="command" value="LOGIN" />
	<input type="text"
		   name="username"
		   maxlength="32"
		   minlength="3"
		   placeholder="Username"
		   required />	
	<br/>
	<input type="password"
		   name="password"
		   maxlength="32"
		   minlength="8"
		   placeholder="Password"
		   required />
	<br/>
	<br/>
	<input type="submit" value="Login" />
</form>
<br/>

<form action="LoginControllerServlet" method="GET">
	<input type="hidden" name="command" value="ACCOUNT_CREATION_PAGE" />
	<input type="submit" value="Create Account" />
</form>

</div>
</div>

</body>
</html>