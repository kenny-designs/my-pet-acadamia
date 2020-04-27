<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Stats</title>
</head>
<body>

<h1>Player Statistics</h1>

<c:choose>
	<c:when test="${empty ACCOUNT_STATS}">
		<h2>Account <em><%= request.getParameter("username") %></em> not found...</h2>
	</c:when>
	
	<c:otherwise>
		<h3>Username: ${ACCOUNT_STATS.username}</h3>
		<h4>
			Battles Won: ${ACCOUNT_STATS.battlesWon} <br>
			Battles Lost: ${ACCOUNT_STATS.battlesLost}
		</h4>
	</c:otherwise>
</c:choose>

<br />
<a href="./home.jsp">Return Home</a>

</body>
</html>