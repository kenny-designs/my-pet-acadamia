<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Team</title>
</head>
<body>

<h1>Manage Team</h1>

<table border="1">
	<tr>
		<th>Name</th>
		<th>Health Type</th>
		<th>Level</th>
		<th>Experience Points</th>
		<th>Photo</th>
	</tr>

<c:forEach var="tempPet" items="${PLAYER_PETS_LIST}">
	<tr>
		<td>${tempPet.pet.name}</td>
		<td>${tempPet.pet.healthType}</td>
		<td>${tempPet.level}</td>
		<td>${tempPet.exp}</td>
		<td><img width="200" src="./images/${tempPet.pet.imageURL}" /></td>
	</tr>
</c:forEach>
</table>

<br />
<a href="./home.jsp">Return Home</a>

</body>
</html>