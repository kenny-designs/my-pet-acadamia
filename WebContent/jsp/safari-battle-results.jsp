<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Safari Results</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${context}/css/safari.css">
<link type="text/css" rel="stylesheet" href="${context}/css/style.css">

<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="${context}/images/favicon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="${context}/images/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="${context}/images/favicon/favicon-16x16.png">
<link rel="manifest" href="${context}/images/favicon/site.webmanifest">
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

<a href="${context}/jsp/safari.jsp">Go to Safari</a>
<br/><br/>
<a href="${context}/jsp/home.jsp">Return Home</a>

</div>
</div>

</body>
</html>