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
<div class="pet-battle-container">
	<div class="card">
	  <img src="./images/${playerBattlePet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1>${playerBattlePet.pet.name} <em>lvl. ${playerBattlePet.level}</em></h1> 
		<label for="player-hp">
			Hp: ${playerBattlePet.hitpoints} / ${playerBattlePet.maxHitpoints}
		</label>	
		<progress id="player-hp"
				  class="health-bar"
				  value="${playerBattlePet.hitpoints}"
				  max="${playerBattlePet.maxHitpoints}">
		</progress>
		<hr />
	  </div>
	</div>

<c:set var="safariBattlePet" scope="request" value="${SAFARI_TEAM.getActiveBattlePet()}"/>
	<div class="card">
	  <img src="./images/${safariBattlePet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1>${safariBattlePet.pet.name} <em>lvl. ${safariBattlePet.level}</em></h1> 	
		<label for="safari-hp">
			Hp: ${safariBattlePet.hitpoints} / ${safariBattlePet.maxHitpoints}
		</label>	
		<progress id="safari-hp"
				  class="health-bar"
				  value="${safariBattlePet.hitpoints}"
				  max="${safariBattlePet.maxHitpoints}">
		</progress>
		<hr />
	  </div>
	</div>
</div>

</body>
</html>