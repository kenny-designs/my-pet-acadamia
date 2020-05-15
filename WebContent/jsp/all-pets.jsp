<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: All Pets</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${context}/css/all-pets.css">
<link type="text/css" rel="stylesheet" href="${context}/css/style.css">

<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="${context}/images/favicon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="${context}/images/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="${context}/images/favicon/favicon-16x16.png">
<link rel="manifest" href="${context}/images/favicon/site.webmanifest">
</head>
<body class="all-pets-bg">

<div id="collection-container">

<a href="${context}/jsp/home.jsp">Return Home</a>
<h1 class="header">Gotta Adopt them All!</h1>
<hr/>

<div class="pet-flex-container">
<c:forEach var="tempPet" items="${PET_LIST}">
	<div class="card">
	  <img src="${context}/images/${tempPet.imageURL}" alt="PetImage" style="width:100%">
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
</div>

</body>
</html>