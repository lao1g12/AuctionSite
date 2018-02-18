<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/lw-style.css" type="text/css" />
<title>Registration</title>
</head>
<body>
<div class="page-container">

		<div id="TitLog" class="row">
			<div class="col col2">
				<img id="logol"
					src="img/Man.jpg">
			</div>
			<div class="col col2">
				<img id="logor"
					src="img/Man.jpg">
			</div>
			<div class="col col4">
				<img id="logoti" src="img/LittleMan.jpg">
			</div>
			<div class="col col2">
				<img id="logor"
					src="img/Man.jpg">
			</div>
			<div class="col col2">
				<img id="logor"
					src="img/Man.jpg">
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
		</br>
<h2>${Incorrect}</h2>
<h2> ${UsernameTaken}</h2>
	<sf:form method="post" action="doRegister" modelAttribute="user">
		First Name <sf:input required="1" type="text" path="person.firstName" /> <br /> 
		Last Name <sf:input required="1" type="text" path="person.lastName" /> <br /> 
		Gender <br /> 
		 <sf:radiobutton path="person.gender" value="Male" /> Male
		 <sf:radiobutton path="person.gender" value="Female" /> Female<br /> 
		Address <sf:input path="person.address" /> <br />
		Email <sf:input required="1" path="person.email" /> <br />
		Interests <sf:input path="person.interests" /> <br />
		Account Details <sf:input path="person.accountDetails" /> <br />
		Username <sf:input required="1" path="username" /> <br /> 
		Password <sf:password required="1" path="password" /> <br /> 
		Confirm Password <sf:password required="1" path="confirmPassword" /> <br /> 
		<input type="submit" value="Register"/>
	</sf:form>
	</br>
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




