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

<h1>You Current Team</h1>
<hr />

<div class="pet-flex-container">
<c:forEach var="tempPet" items="${PLAYER_PETS_TEAM}">
	<div class="card">
	  <img src="./images/${tempPet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1><b>${tempPet.pet.name}</b></h1> 
	
		<form action="PetsControllerServlet" method="POST">
			<input type="hidden" name="command" value="REMOVE_PET_FROM_TEAM" />
			<input type="hidden" name="playerPetId" value="${tempPet.id}" />		
			<input type="submit" value="Remove"
			<c:if test="${PLAYER_PETS_TEAM.size() == 1}">disabled</c:if> />
		</form>
		
		<hr />
		<p>Health Type: ${tempPet.pet.healthType}</p> 
		<p>Level: ${tempPet.level}</p>
		<p>Experience Points: ${tempPet.exp}</p>
		<hr />
		<p><em>${tempPet.pet.description}</em></p>
	  </div>
	</div>
</c:forEach>
</div>


<h1>Rest of Your Pet Collection</h1>
<hr />
<div class="pet-flex-container">
<c:forEach var="tempPet" items="${PLAYER_PETS_COLLECTION}">
	<div class="card">
	  <img src="./images/${tempPet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1><b>${tempPet.pet.name}</b></h1> 
		
		<form action="PetsControllerServlet" method="POST">
			<input type="hidden" name="command" value="DELETE_PLAYER_PET" />
			<input type="hidden" name="playerPetId" value="${tempPet.id}" />		
			<input type="submit"
				value="Delete Pet"
				onclick="return confirm('Wow! Are you really going to MURDER ${tempPet.pet.name}?')" />
		</form>
		
		<form action="PetsControllerServlet" method="POST">
			<input type="hidden" name="command" value="ADD_PET_TO_TEAM" />
			<input type="hidden" name="playerPetId" value="${tempPet.id}" />		
			<input type="submit" value="Add"			
			<c:if test="${PLAYER_PETS_TEAM.size() == 3}">disabled</c:if> />
		</form>			

		<hr />
		<p>Health Type: ${tempPet.pet.healthType}</p> 
		<p>Level: ${tempPet.level}</p>
		<p>Experience Points: ${tempPet.exp}</p>
		<hr />
		<p><em>${tempPet.pet.description}</em></p>
	  </div>
	</div>	
</c:forEach>
</div>

</body>
</html>