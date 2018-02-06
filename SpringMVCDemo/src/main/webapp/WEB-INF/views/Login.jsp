<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/lw-style.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<div class="page-container">

		<div id="TitLog" class="row">
			<div class="col col2">
				<img id="logol"
					src="https://image.freepik.com/free-photo/auctioneer_1048-1642.jpg">
			</div>
			<div class="col col2">
				<img id="logor"
					src="https://image.freepik.com/free-photo/auctioneer_1048-1642.jpg">
			</div>
			<div class="col col4">
				<img id="logoti" src="img/title.png">
			</div>
			<div class="col col2">
				<img id="logor"
					src="https://image.freepik.com/free-photo/auctioneer_1048-1642.jpg">
			</div>
			<div class="col col2">
				<img id="logor"
					src="https://image.freepik.com/free-photo/auctioneer_1048-1642.jpg">
			</div>
		</div>

		<div id="HeadLog" class="row">
			<div class="col col7">
				<ul class="horiz">
					<li class="horizl"><a href="goHome">Home</a></li>
					<li class="horizl"><a href="www.categories.com">Categories</a></li>
					<li class="horizl"><a href="www.suggested.com">Suggested</a></li>
					<li class="horizl"><a href="www.deals.com">Deals</a></li>
					<li class="horizl"><a href="www.shops.com">Shops</a></li>
				</ul>
			</div>
			<div class="col col5 last">
				<ul class="horiz">
					<li class="horizl"><a href="signup">Sign Up</a></li>
					<li class="horizl"><form action="j_security_check"
							method="post">
							<input id="login" name="j_username" placeholder="Enter Login" /> <input
								id="login" type="password" name="j_password" placeholder="Enter Password" />
							<input id="login" type="submit" value="Login">
						</form></li>


				</ul>

			</div>
		</div>
<h2>It looks like your login details were wrong!</h2>
<h2>Try again below or sign up for a new account!</h2>
<form action="j_security_check" method="post">
	Username: <input name="j_username" /> <br/>
	Password: <input type="password" name="j_password" /> 
	<input type="Submit" value="Login" />
</form>
<h2> Not got an account? Sign up here</h2>
<a href="signup">Sign Up</a>

	<div>
		<footer>
			<ul class="horiz">
				<li class="horizl"><a href="www.ContactUs.com">Contact Us</a></li>
				<li class="horizl"><a href="www.Help.com">Help</a></li>
				<li class="horizl"><a href="www.language.com">Language</a></li>
				<li class="horizl"><a href="www.about.com">About</a></li>
				<li class="horizl"><a href="www.SiteMap.com">Site Map</a></li>
			</ul>
		</footer>
	</div>
	</div>
</body>
</html>