<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Team</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${context}/css/manage-team.css">
<link type="text/css" rel="stylesheet" href="${context}/css/style.css">
</head>
<body class="manage-team-bg">

<div id="team-container">
<a href="${context}/jsp/home.jsp">Return Home</a>

<h1 class="header">Your Current Team</h1>
<hr />

<div class="pet-flex-container">
<c:forEach var="tempPet" items="${PLAYER_PETS_TEAM}">
	<div class="card">
	  <img src="${context}/images/${tempPet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1><b>${tempPet.pet.name}</b></h1> 		
		<h2><em>lvl. ${tempPet.level}</em></h2>
	
		<form action="PetsControllerServlet" method="POST">
			<input type="hidden" name="command" value="REMOVE_PET_FROM_TEAM" />
			<input type="hidden" name="playerPetId" value="${tempPet.id}" />		
			<input type="submit" value="Remove"
			<c:if test="${PLAYER_PETS_TEAM.size() == 1}">disabled</c:if> />
		</form>
		
		<hr />
		<p>Health Type: ${tempPet.pet.healthType.toUpperCase()}</p> 
		<label for="pet-exp">
			Exp: ${tempPet.exp} / 1000
		</label>	
		<progress id="pet-exp"
				  class="health-bar"
				  value="${tempPet.exp}"
				  max="1000">
		</progress>

		
		<hr />
		<p><em>${tempPet.pet.description}</em></p>
	  </div>
	</div>
</c:forEach>
</div>


<h1 class="header">Rest of Your Pet Collection</h1>
<hr />
<div class="pet-flex-container">
<c:forEach var="tempPet" items="${PLAYER_PETS_COLLECTION}">
	<div class="card">
	  <img src="${context}/images/${tempPet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1><b>${tempPet.pet.name}</b></h1> 		
		<h2><em>lvl. ${tempPet.level}</em></h2>
		
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
		<p>Health Type: ${tempPet.pet.healthType.toUpperCase()}</p> 
			
		<label for="pet-exp">
			Exp: ${tempPet.exp} / 1000
		</label>	
		<progress id="pet-exp"
				  class="health-bar"
				  value="${tempPet.exp}"
				  max="1000">
		</progress>
		
		<hr />
		<p><em>${tempPet.pet.description}</em></p>
	  </div>
	</div>	
</c:forEach>
</div>
</div>

</body>
</html>