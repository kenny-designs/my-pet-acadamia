<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Safari Caught</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${context}/css/safari-catch.css">
<link type="text/css" rel="stylesheet" href="${context}/css/style.css">
</head>
<body>

<div class="safari-bg">
<div class="middle">
	<div class="card">
	  <img src="${context}/images/${CAUGHT_PLAYER_PET.pet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1><b>${CAUGHT_PLAYER_PET.pet.name}</b></h1> 		
		<h2><em>lvl. ${CAUGHT_PLAYER_PET.level}</em></h2>
			
		<hr />
		<p>Health Type: ${CAUGHT_PLAYER_PET.pet.healthType.toUpperCase()}</p> 
		<label for="pet-exp">
			Exp: ${CAUGHT_PLAYER_PET.exp} / 1000
		</label>	
		<progress id="pet-exp"
				  class="health-bar"
				  value="${CAUGHT_PLAYER_PET.exp}"
				  max="1000">
		</progress>

		
		<hr />
		<p><em>${CAUGHT_PLAYER_PET.pet.description}</em></p>
	  </div>
	</div>

<div>
	<h1>You Caught ${CAUGHT_PLAYER_PET.pet.name}!</h1>
	<a href="${context}/jsp/safari.jsp">Go to Safari</a>
	<br/><br/>
	<form action="PetsControllerServlet" method="POST">
		<input type="hidden" name="command" value="MANAGE_TEAM" />
		<input type="submit" value="Manage Team" />
	</form>	
	<br/>
	<a href="${context}/jsp/home.jsp">Return Home</a>
</div>

</div>
</div>

</body>
</html>