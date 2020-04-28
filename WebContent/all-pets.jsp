<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: All Pets</title>
</head>
<body>

<h1>Gotta Adopt them All!</h1>

<a href="./home.jsp">Return Home</a>

<table border="1">
	<tr>
		<th>Name</th>
		<th>Health Type</th>
		<th>Photo</th>
	</tr>

<c:forEach var="tempPet" items="${PET_LIST}">
	<tr>
		<td>${tempPet.name}</td>
		<td>${tempPet.healthType}</td>
		<td><img width="200" src="./images/${tempPet.imageURL}" /></td>
	</tr>
</c:forEach>
</table>

</body>
</html>