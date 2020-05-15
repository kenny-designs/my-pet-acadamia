<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Safari</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${context}/css/safari.css">
<link type="text/css" rel="stylesheet" href="${context}/css/style.css">
<script src="${context}/js/util.js"></script>
</head>
<body>

<div class="safari-bg"></div>

<div class="middle">
<h1>Safari.</h1>

<c:choose>
	<c:when test="${HAS_SAFARI_BATTLE}">
		<h3>You have an ongoing battle!</h3>
		<form action="BattleControllerServlet" method="POST" onsubmit="disableAllInputSubmit()">
			<input type="hidden" name="command" value="LOAD_SAFARI_BATTLE" />
			<input type="submit" value="Finish the Fight!" />
		</form>

	</c:when>
	
	<c:otherwise>
		<h3>No battle. Find one?</h3>
		<form action="BattleControllerServlet" method="POST" onsubmit="disableAllInputSubmit()">
			<input type="hidden" name="command" value="NEW_SAFARI_BATTLE" />
			<input type="submit" value="Fight Random Pet!" />
		</form>
	</c:otherwise>
</c:choose>
<br/>
<a href="${context}/jsp/home.jsp">Return Home</a>
</div>

</body>
</html>