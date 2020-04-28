<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Account Creation</title>
</head>
<body>

<h1>Create a new account!</h1>

<form action="LoginControllerServlet" method="POST">
	<input type="hidden" name="command" value="CREATE_ACCOUNT" />

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
				<td><label>Pet: </label></td>
				<td>
					<input type="radio" id="cat" name="petName" value="Cat" checked>
					<label for="cat">Cat</label>
				</td>
				
				<td>
					<input type="radio" id="weezer" name="petName" value="Weezer">
					<label for="weezer">Weezer</label>
				</td>
				
				<td>
					<input type="radio" id="chuck" name="petName" value="Chuck">
					<label for="chuck">Chuck</label>
				</td>
			</tr>
					
			<tr>
				<td><label></label></td>
				<td><input type="submit" value="Create Account" /></td>
			</tr>
		</tbody>
	</table>
</form>

<br/>
<a href="./index.jsp">Return to Login</a>

</body>
</html>