<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Pet Acadamia: Home</title>
<link type="text/css" rel="stylesheet" href="css/home.css">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<div id="overlay" onclick="off()">
  <div id="text"> 
	Ever since I was a kid, I was always very fascinated with engaging in a
	living virtual world (one akin to that of Massively Multiplayer Online (MMO)
	games such as World of Warcraft). One of my very first experiences with such
	a game was an old website known as Neopets. The goal of Neopets was that
	you and every other person on the site makes an account and raises virtual
	pets. Players could play minigames to earn money, care for their fictional
	animals, or level them up and have them battle non-playable characters or
	even other real people!
	<br/><br/>
	I quite enjoyed the thought of player versus player combat as a kid and
	always wanted to have a competitive game of my own. One that took place
	in a persistent world inhabited by other real people. As such, I decided
	to attempt to realize this dream through this project I humorously refer
	to as, "Boku No Pet Acadamia: Budokai Tenkaichi 3 Ultra Edition!" This
	roughly translates to, "My Pet Acadamia: Number One Under the Heavens
	Martial Arts Tournament 3 Ultra Edition!" in Japanese.
	<br/><br/>
	I was unable to complete this game as I had fully envisioned it (it lacks
	player versus player and only basic attacks have been implemented). Even
	so, players can still create an account, battle random enemies, catch pets,
	and level up their team. If I had more time, I would continue to flesh out
	the combat but I'm happy I was even able to get it in working condition. 
	<br/><br/>
	Thank you to everyone that tested the game and helped to make sure it at
	least worked! 
  </div>
</div>

<div class="home-bg">
<div class="middle">
<h1>Welcome ${LOGGED_USER.username}!</h1>

<form action="LoginControllerServlet" method="GET">
	<input type="hidden" name="command" value="DISPLAY_ACCOUNT_STATS" />
	<input type="hidden" name="username" value="${LOGGED_USER.username}" />
	<input type="submit" value="Stats" />
</form>

<form action="PetsControllerServlet" method="POST">
	<input type="hidden" name="command" value="MANAGE_TEAM" />
	<input type="submit" value="Manage Team" />
</form>

<form action="BattleControllerServlet" method="POST">
	<input type="hidden" name="command" value="ENTER_SAFARI" />
	<input type="submit" value="Enter Safari" />
</form>

<form action="PetsControllerServlet" method="GET">
	<input type="hidden" name="command" value="DISPLAY_ALL_PETS" />
	<input type="submit" value="View PetWiki" />
</form>

<form action="LoginControllerServlet" method="POST">
	<input type="hidden" name="command" value="LOGOUT" />
	<input type="submit" value="Logout" />
</form>

<button class="glow" onclick="on()">Inspiration</button>

</div>
</div>

<script>
function on() {
  document.getElementById("overlay").style.display = "block";
  document.getElementsByClassName("middle")[0].style.display = "none";
}

function off() {
  document.getElementById("overlay").style.display = "none";
  document.getElementsByClassName("middle")[0].style.display = "block";
}
</script>

</body>
</html>