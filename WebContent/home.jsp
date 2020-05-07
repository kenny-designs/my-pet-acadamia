<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Home</title>
</head>
<body>

<h1>Welcome Home ${LOGGED_USER.username}!</h1>

<form action="LoginControllerServlet" method="GET">
	<input type="hidden" name="command" value="DISPLAY_ACCOUNT_STATS" />
	<input type="hidden" name="username" value="${LOGGED_USER.username}" />
	<input type="submit" value="Stats" />
</form>

<form action="PetsControllerServlet" method="POST">
	<input type="hidden" name="command" value="MANAGE_TEAM" />
	<input type="submit" value="Manage Team" />
</form>

<form action="BattleControllerServlet" method="POST">
	<input type="hidden" name="command" value="ENTER_SAFARI" />
	<input type="submit" value="Enter Safari" />
</form>

<form action="PetsControllerServlet" method="GET">
	<input type="hidden" name="command" value="DISPLAY_ALL_PETS" />
	<input type="submit" value="View PetWiki" />
</form>

<form action="LoginControllerServlet" method="POST">
	<input type="hidden" name="command" value="LOGOUT" />
	<input type="submit" value="Logout" />
</form>

</body>
</html>