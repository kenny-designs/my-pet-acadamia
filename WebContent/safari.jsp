<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Safari</title>
</head>
<body>

<h1>Safari</h1>

<c:choose>
	<c:when test="${HAS_SAFARI_BATTLE}">
		<h3>You have a battle going!</h3>
	</c:when>
	
	<c:otherwise>
		<h3>No battle. Find one?</h3>
	</c:otherwise>
</c:choose>


<a href="./safari-battle.jsp">Train in Safari</a>
<br />
<a href="./home.jsp">Return Home</a>

</body>
</html>