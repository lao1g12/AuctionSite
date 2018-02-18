<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
					<li class="horizl"> <h4>Welcome ${username}</h4></li>
					<li class="horizl"><a href="logout">Logout</a></li>
					<li class="horizl"><a href="account">Account</a></li>


				</ul>
			</div>
		</div>
		<div>
			<form method="post" action="doReview">
				<c:choose>
					<c:when test='${role == "buyer"}'>
						<input type="hidden" name="buyer" value="${oldListing.winningUser.username}"/>
						<input type="hidden" name="seller" value="${oldListing.user.username}"/>
						<input type="hidden" name="oldListing" value="${oldListing.listingId}"/>
						<input type="hidden" name="reviewer" value="buyer"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="seller" value="${oldListing.winningUser.username}"/>
						<input type="hidden" name="buyer" value="${oldListing.user.username}"/>
						<input type="hidden" name="oldListing" value="${oldListing.listingId}"/>
						<input type="hidden" name="reviewer" value="seller"/>
					</c:otherwise>
				</c:choose>
			Title : <input required type="text" name="title"/><br>
			Body : <input required type="text" name="body"/><br>
			Rating : <select name="rating" >
			<option value="1"> 1 </option>
			<option value="2"> 2 </option>
			<option value="3"> 3 </option>
			<option value="4"> 4 </option>
			<option value="5"> 5 </option>
			</select><br>
				<input type="submit" value="Place Review"/>
			</form>
		
		</div>
</div>
</body>
</html>