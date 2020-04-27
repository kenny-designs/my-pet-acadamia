<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia!</title>
</head>
<body>

<h1>Boku No Pet Acadamia: Budokai Tenkaichi 3 Ultra Edition</h1>

<c:if test="${LOGIN_FAILED}">
	<p style="color: red;">Incorrect username and/or password!</p>
</c:if>

<form action="LoginControllerServlet" method="POST">
	<input type="hidden" name="command" value="LOGIN" />
	<table>
		<tbody>
			<tr>
				<td><label>Username: </label></td>
				<td>
					<input 
						type="text"
						name="username" 
						maxlength="32"
						minlength="3"
						required
					/>
				</td>
			</tr>
			
			<tr>
				<td><label>Password: </label></td>
				<td>
					<input
						type="password"
						name="password"
						maxlength="32"
						minlength="8"
						required
					/>
				</td>
			</tr>
							
			<tr>
				<td><label></label></td>
				<td><input type="submit" value="Login" /></td>
			</tr>
		</tbody>
	</table>
</form>

<br/>
<a href="./create-account.jsp">Create Account</a>

</body>
</html>