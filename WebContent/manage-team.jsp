<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Team</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<h1>Manage Team</h1>

<a href="./home.jsp">Return Home</a>

<!--
<table border="1">
	<tr>
		<th>Name</th>
		<th>Health Type</th>
		<th>Level</th>
		<th>Experience Points</th>
		<th>Photo</th>
		<th>Manage Pet</th>
		<th>Is Team</th>
	</tr>
-->

<c:forEach var="tempPet" items="${PLAYER_PETS_LIST}">
	<div class="card">
	  <img src="./images/${tempPet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1><b>${tempPet.pet.name}</b></h1> 
		<hr />
		<p>Health Type: ${tempPet.pet.healthType}</p> 
		<p>Level: ${tempPet.level}</p>
		<p>Experience Points: ${tempPet.exp}</p>
		<hr />
		<p><em>${tempPet.pet.description}</em></p>
	  </div>
	</div>

<!--
	<tr>
		<td>${tempPet.pet.name}</td>
		<td>${tempPet.pet.healthType}</td>
		<td>${tempPet.level}</td>
		<td>${tempPet.exp}</td>
		<td><img width="200" src="./images/${tempPet.pet.imageURL}" /></td>		
		<td>
			<form action="PetsControllerServlet" method="POST">
				<input type="hidden" name="command" value="DELETE_PLAYER_PET" />
				<input type="hidden" name="playerPetId" value="${tempPet.id}" />
			
				<input type="submit"
					   value="Delete Pet"
					   onclick="return confirm('Wow! Are you really going to MURDER ${tempPet.pet.name}?')"
					   <c:if test="${PLAYER_PETS_LIST.size() == 1}">disabled</c:if> />
			</form>		
		</td>
		<td>${tempPet.team}</td>
	</tr>
	-->
</c:forEach>
<!--
</table>
-->

</body>
</html>