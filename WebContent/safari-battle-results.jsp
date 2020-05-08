<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Safari Results</title>
<link type="text/css" rel="stylesheet" href="css/safari.css">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="safari-bg">
<div class="middle">

<c:choose>
	<c:when test="${IS_WINNER}">
		<h1>${LOGGED_USER.username} Wins!</h1>
		<h2>Team gained 300 exp!</h2>
	</c:when>
	
	<c:otherwise>
		<h1>Loser...</h1>
	</c:otherwise>
</c:choose>

<a href="./safari.jsp">Go to Safari</a>
<br/><br/>
<a href="./home.jsp">Return Home</a>

</div>
</div>

</body>
</html>