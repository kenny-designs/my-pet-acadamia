<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Safari Battle</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<h1>Safari Battle</h1>

<a href="./home.jsp">Return Home</a>

<c:set var="playerBattlePet" scope="request" value="${PLAYER_TEAM.getActiveBattlePet()}"/>
<div class="pet-flex-container">
	<div class="card">
	  <img src="./images/${playerBattlePet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1><b>${playerBattlePet.pet.name}</b></h1> 
			
		<hr />
		<p>HP: ${playerBattlePet.hitPoints}</p> 
		<p>Level: ${playerBattlePet.level}</p>
	  </div>
	</div>
</div>

<c:set var="safariBattlePet" scope="request" value="${SAFARI_TEAM.getActiveBattlePet()}"/>
<div class="pet-flex-container">
	<div class="card">
	  <img src="./images/${safariBattlePet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1><b>${safariBattlePet.pet.name}</b></h1> 
			
		<hr />
		<p>HP: ${safariBattlePet.hitPoints}</p> 
		<p>Level: ${safariBattlePet.level}</p>
	  </div>
	</div>
</div>

</body>
</html>