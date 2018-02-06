<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/lw-style.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<div class="page-container">
							<h3>${NoListings}</h3>

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
				<img id="logoti" src="https://photos-5.dropbox.com/t/2/AAD9l6W2svDqoYzoX0TYGbHi_-jXos4gjRFNylL_572OMg/12/97270925/jpeg/32x32/1/_/1/2/LittleMan.jpg/EM3Ju0sY7AYgBygH/vTRta-9g1_qbiWR8Qf9TtX6vNL6Q-giqOPmWo0-nz5o?size=2048x1536&size_mode=3">
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
					<li class="horizl"> <h4>Welcome ${username}</h4></li>
					<li class="horizl"><a href="logout">Logout</a></li>
					<li class="horizl"><a href="account">Account</a></li>


				</ul>
			</div>
		</div>
		
	<h2>Welcome to your account update info page ${username}</h2>
	<h2>${Incorrect}</h2>
	Profile information:
		<sf:form method="post" action="doUpdateInfo" modelAttribute="user">
			</br> First Name : <sf:input required="1" type="text" path="person.firstName" value="${person.getFirstName()}"/>
			</br> Last Name : <sf:input required="1" type="text" path="person.lastName" value="${person.getLastName()}"/>
			</br> Gender : <sf:select path="person.gender" value="${person.getGender()}">
                           			<sf:option value="Male">Male</sf:option>
                           			<sf:option value="Female">Female</sf:option>
              				  </sf:select>
			</br> Address : <sf:input required="1" type="text" path="person.address" value="${person.getAddress()}"/>
			</br> Email : <sf:input required="1" type="text" path="person.email" value="${person.getEmail()}"/>
			</br> Interests : <sf:input required="1" type="text" path="person.interests" value="${person.getInterests()}"/>
			</br> Account Details : <sf:input required="1" type="text" path="person.accountDetails" value="${person.getAccountDetails()}"/>
			</br> Please confirm your password : <sf:password required="1" path="password"/>
			<sf:hidden path="username" value="${username}"/>
			<sf:hidden path="role" value="${user.getRole()}"/>
			<sf:hidden path="person.id" value="${person.getId()}"/>
			<sf:hidden path="person.startDate" value="${person.getStartDate()}"/>
			</br> <input type="submit" value="Update details"/>
		</sf:form>
		</rl>
			<h2>Would you like to set a new password instead?</h2></br>
			<h2>${passNotMatch}</h2>
			<h2>${incorrectPass}</h2>
			<form method="post" action="passwordUpdate">
			Enter Original password : <input required type="password" name="password"/>
			Enter Your new password : <input required type="password" name="newPassword"/>
			Please confirm your NEW password : <input required type="password" name="confNewPassword"/>
			<input type="submit" value="Update details"/>
			</form>
			
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
</body>
</html>