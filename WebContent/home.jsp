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

<br />
<a href="./stats.jsp">Stats</a>
<br />
<a href="./manage-team.jsp">Manage Team</a>
<br />
<a href="./safari.jsp">Safari</a>
<br />
<a href="./battledome.jsp">Battledome</a>

<form action="LoginControllerServlet" method="POST">
	<input type="hidden" name="command" value="LOGOUT" />
	<input type="submit" value="Logout" />
</form>

</body>
</html>