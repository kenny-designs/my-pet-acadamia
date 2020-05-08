<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia!</title>
<link type="text/css" rel="stylesheet" href="css/index.css">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<!--
<h1>Boku No Pet Acadamia: Budokai Tenkaichi 3 Ultra Edition</h1>
-->


<div class="index-bg">
<div class="middle">

<!--
<img src="./images/header-logo.png">
-->


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