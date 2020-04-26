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

<form action="./">
	<table>
		<tbody>
			<tr>
				<td><label>Username: </label></td>
				<td><input type="text" name="username" /></td>
			</tr>
			
			<tr>
				<td><label>Password: </label></td>
				<td><input type="text" name="password" /></td>
			</tr>			
			
			<tr>		
				<td><label>Pet: </label></td>
				<td>
					<input type="radio" id="cat" name="pet" value="cat">
					<label for="cat">Cat</label>
				</td>
				
				<td>
					<input type="radio" id="weezer" name="pet" value="weezer">
					<label for="weezer">Weezer</label>
				</td>
				
				<td>
					<input type="radio" id="chuck" name="pet" value="chuck">
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