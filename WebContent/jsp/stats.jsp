<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Stats</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${context}/css/stats.css">
<link type="text/css" rel="stylesheet" href="${context}/css/style.css">

<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="${context}/images/favicon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="${context}/images/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="${context}/images/favicon/favicon-16x16.png">
<link rel="manifest" href="${context}/images/favicon/site.webmanifest">
</head>
<body>

<div class="stats-bg"></div>
<div class="middle">
<h1>Player Statistics</h1>

<c:choose>
	<c:when test="${empty ACCOUNT_STATS}">
		<h2>Account <em><%= request.getParameter("username") %></em> not found...</h2>
	</c:when>
	
	<c:otherwise>
		<h2>${ACCOUNT_STATS.username}</h2>
		<h3>
			Safari Battles Won: ${ACCOUNT_STATS.safariBattlesWon} <br>
			Safari Battles Lost: ${ACCOUNT_STATS.safariBattlesLost}
		</h3>
	</c:otherwise>
</c:choose>

<br />
<a href="${context}/jsp/home.jsp">Return Home</a>
</div>

</body>
</html>