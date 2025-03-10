<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Safari Battle</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${context}/css/safari-battle.css">
<link type="text/css" rel="stylesheet" href="${context}/css/style.css">
<script src="${context}/js/util.js"></script>

<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="${context}/images/favicon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="${context}/images/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="${context}/images/favicon/favicon-16x16.png">
<link rel="manifest" href="${context}/images/favicon/site.webmanifest">
</head>
<body>

<div class="battle-bg">
<div class="middle">

<img class="versus" src="${context}/images/versus.png">

<!-- Create variables -->
<c:set var="playerBattlePet" scope="request" value="${PLAYER_TEAM.getActiveBattlePet()}"/>
<c:set var="safariBattlePet" scope="request" value="${SAFARI_TEAM.getActiveBattlePet()}"/>

<div class="pet-battle-container">
	<!-- Display inactive pets -->
	<div class="inactive-pet-container">
	<c:forEach var="playerInactivePet" items="${PLAYER_TEAM.getInactiveBattlePets()}">
	<div class="card">
		<div style="display: flex; justify-content: space-evenly;">
			<img src="${context}/images/${playerInactivePet.pet.imageURL}" alt="PetImage">
			<form action="BattleControllerServlet" method="POST" onsubmit="disableAllInputSubmit()">
				<input type="hidden" name="command" value="SWAP_SAFARI_BATTLE" />
				<input type="hidden" name="team-id" value="${PLAYER_TEAM.id}" />
				<input type="hidden" name="inactive-battle-pet-id" value="${playerInactivePet.id}" />
				<input type="hidden" name="active-battle-pet-id" value="${playerBattlePet.id}" />
				<input type="hidden" name="safari-battle-pet-id" value="${safariBattlePet.id}" />
				<input type="submit"
					   value="Swap"
					   <c:if test="${playerInactivePet.dead}">disabled</c:if>/>
			</form>	
		</div>
	  <div class="container" style="margin-top: 5px;">
		<h4 style="margin-bottom: 0; text-align: center;">
			lvl. ${playerInactivePet.level} |
			Hp. ${playerInactivePet.hitpoints} / ${playerInactivePet.maxHitpoints}
		</h4>
	  </div>
	</div>	
	</c:forEach>	
	<a href="${context}/jsp/home.jsp">Return Home</a>
	
	<br/><br/>
	
	<form action="BattleControllerServlet" method="POST" onsubmit="disableAllInputSubmit()">
		<input type="hidden" name="command" value="CATCH_PET" />
		<input type="hidden" name="battle-pet-id" value="${safariBattlePet.id}" />
		<input type="submit"
			   value="Catch ${safariBattlePet.pet.name}"
			   <c:if test="${!safariBattlePet.catchable}">disabled</c:if>/>
	</form>
	</div>
	
	<div class="card">
	  <img src="${context}/images/${playerBattlePet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1>${playerBattlePet.pet.name}</h1> 
		<h2><em>lvl. ${playerBattlePet.level}</em></h2>
		<label for="player-hp">
			Hp. ${playerBattlePet.hitpoints} / ${playerBattlePet.maxHitpoints}
		</label>	
		<progress id="player-hp"
				  class="health-bar"
				  value="${playerBattlePet.hitpoints}"
				  max="${playerBattlePet.maxHitpoints}">
		</progress>
		<hr />
		
		<!-- Display available skills -->	
		<c:forEach var="skillName" items="${playerBattlePet.pet.skills}">
			<form action="BattleControllerServlet" method="POST" onsubmit="disableAllInputSubmit()">
				<input type="hidden" name="command" value="SKILL_SAFARI_BATTLE" />
				<input type="hidden" name="skill-name" value="${skillName}" />
				<input type="hidden" name="player-battle-pet-id" value="${playerBattlePet.id}" />
				<input type="hidden" name="safari-battle-pet-id" value="${safariBattlePet.id}" />
				<input type="submit"
					   value="${skillName}"
					   <c:if test="${playerBattlePet.dead}">disabled</c:if>/>
			</form>
		</c:forEach>
	  </div>
	</div>
	
	<div class="card">
	  <img src="${context}/images/${safariBattlePet.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1>${safariBattlePet.pet.name}</h1> 	
		<h2><em>lvl. ${safariBattlePet.level}</em></h2>
		<label for="safari-hp">
			Hp. ${safariBattlePet.hitpoints} / ${safariBattlePet.maxHitpoints}
		</label>	
		<progress id="safari-hp"
				  class="health-bar"
				  value="${safariBattlePet.hitpoints}"
				  max="${safariBattlePet.maxHitpoints}">
		</progress>
		<hr />
	  </div>
	</div>
	
	<!-- Display safari inactive pets -->
	<div class="inactive-pet-container">
	<c:forEach var="safariInactivePet" items="${SAFARI_TEAM.getInactiveBattlePets()}">
	<div class="card">
		<div style="display: flex; justify-content: space-evenly;">
			<img src="${context}/images/${safariInactivePet.pet.imageURL}" alt="PetImage">	
		</div>
	  <div class="container" style="margin-top: 5px;">
		<h4 style="margin-bottom: 0; text-align: center;">
			lvl. ${safariInactivePet.level} |
			Hp. ${safariInactivePet.hitpoints} / ${safariInactivePet.maxHitpoints}
		</h4>
	  </div>
	</div>	
	</c:forEach>
	</div>	
</div>
</div>
</div>

</body>
</html>