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

<a href="./home.jsp">Return Home</a>

<c:choose>
	<c:when test="${HAS_SAFARI_BATTLE}">
		<h3>You have a battle going!</h3>
	</c:when>
	
	<c:otherwise>
		<h3>No battle. Find one?</h3>
		<form action="BattleControllerServlet" method="POST">
			<input type="hidden" name="command" value="NEW_SAFARI_BATTLE" />
			<input type="submit" value="Fight Random Pet!" />
		</form>
	</c:otherwise>
</c:choose>

</body>
</html>