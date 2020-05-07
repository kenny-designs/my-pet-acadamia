<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: All Pets</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body class="wiki-bg">

<h1>Gotta Adopt them All!</h1>

<a href="./home.jsp">Return Home</a>

<div class="pet-flex-container">
<c:forEach var="tempPet" items="${PET_LIST}">
	<div class="card">
	  <img src="./images/${tempPet.imageURL}" alt="PetImage" style="width:100%">
	  <div class="container">
		<h1><b>${tempPet.name}</b></h1> 
		<hr />
		<p>Health Type: ${tempPet.healthType}</p> 
		<hr />
		<p><em>${tempPet.description}</em></p>
	  </div>
	</div>
</c:forEach>
</div>

</body>
</html>