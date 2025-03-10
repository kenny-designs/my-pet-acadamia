<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Account Creation</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${context}/css/create-account.css">
<link type="text/css" rel="stylesheet" href="${context}/css/style.css">
<script src="${context}/js/util.js"></script>

<!-- favicon -->
<link rel="apple-touch-icon" sizes="180x180" href="${context}/images/favicon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="${context}/images/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="${context}/images/favicon/favicon-16x16.png">
<link rel="manifest" href="${context}/images/favicon/site.webmanifest">
</head>
<body>

<div class="create-account-bg">
<div class="middle">
<form action="LoginControllerServlet"
	  method="POST"
	  onsubmit="disableAllInputSubmit()">
	<input type="hidden" name="command" value="CREATE_ACCOUNT" />
	
<div class="pet-row-container">	
	<c:forEach var="tempPet" items="${STARTER_PETS}">
	<label>	
		<input type="radio" id="${tempPet.name}" name="petName" value="${tempPet.name}"
		<c:if test="${tempPet.getName().equals(\"Cat\")}">checked</c:if> />
		<div class="card small">
	  		<img src="${context}/images/${tempPet.imageURL}" alt="PetImage" style="width:100%">
	  		<div class="container">
				<h1><b>${tempPet.name}</b></h1> 
				<hr />
				<p><em>${tempPet.description}</em></p>
	  		</div>
		</div>
	</label>
	</c:forEach>
</div>

	<input type="text"
		   name="username" 
		   maxlength="32"
		   minlength="3"
		   placeholder="Username"
		   <c:if test="${CREATION_FAILED}">
		   		value="${USERNAME}"
		   		style="border: 2px solid red;"
		   </c:if>
	   
		   required />
	<br/>
	<input type="password"
		   name="password"
		   maxlength="32"
		   minlength="8"
		   placeholder="Password"
		   required />
	<br/>
	
	<input type="submit" value="Create Account" />
	<br/>
	<a href="${context}/index.jsp">Return to Login</a>
</form>

</div>
</div>

</body>
</html>