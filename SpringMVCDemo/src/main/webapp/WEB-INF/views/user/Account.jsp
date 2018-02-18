<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		
	<h2>Welcome to your account page ${username}</h2>
	<h2>${UpdatedPass}</h2>
	<h2>${UpdatedInfo}</h2>
	Profile information:
		</br> Username : ${username}
		</br> Rating : ${user.getRating()}
		</br> First Name : ${person.getFirstName()}
		</br> Last Name : ${person.getLastName()}
		</br> Gender : ${person.getGender()}
		</br> Address : ${person.getAddress()}
		</br> Email : ${person.getEmail()}
		</br> Interests : ${person.getInterests()}
		</br> Account Details : ${person.getAccountDetails()}
		</br> Date of Sign up : ${person.getStartDateFormat()}
		</br>
		Would like to update the info or change your password? Click below!</br>
		<a href="updateInfo">Update my details</a>
		</hr>
	<h2>Current Items you are winning</h2>
		<c:forEach items="${winningListings}" var="wl">
			Listing title :  ${wl.name}<br />
			Listing ID : ${wl.listingId}<br />
			Listing description : ${wl.description}<br />
			Current Price : ${wl.currentPrice}<br />
			End Time : ${wl.getEndDateFormat()}<br/>
			<img id="AccountImg" src="${wl.image}">
			<hr />
		</c:forEach>
		</hr>
	<h2> Items you have won and awaiting payment</h2>
		<c:forEach items="${wonListings}" var="wl">
			Listing title :  ${wl.name}<br />
			Listing ID : ${wl.listingId}<br />
			Listing description : ${wl.description}<br />
			Final Price : ${wl.finalPrice}<br />
			End Time : ${wl.getEndDateFormat()}<br/>
			<img id="AccountImg" src="${wl.image}"></br>
			<c:choose>
				<c:when test="${wl.paid == 'paid'}">
					<c:choose>
						<c:when test="${wl.reviewed == 'reviewed'}">
						</c:when>
						<c:when test="${wl.reviewed == 'buyer'}">
						</c:when>
						<c:otherwise>
							<a href="goToPlaceReviewBought?id=${wl.listingId}">Review</a>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<a href="pay?id=${wl.listingId}">Pay</a>
				</c:otherwise>
			</c:choose>
			<hr />
		</c:forEach>
		</hr>
	
		
	<h2>Current Items you are selling</h2>
		<c:forEach items="${userListings}" var="ul">
			Listing title :  ${ul.name}<br />
			Listing ID : ${ul.listingId}<br />
			Listing description : ${ul.description}<br />
			Current Price : ${ul.currentPrice}<br />
			End Time : ${ul.getEndDateFormat()}<br/>
			<img id="AccountImg" src="${ul.image}">
			<hr />
		</c:forEach>
		
			<h2> Items you have sold</h2>
		<c:forEach items="${soldListings}" var="sol">
			Listing title :  ${sol.name}<br />
			Listing ID : ${sol.listingId}<br />
			Listing description : ${sol.description}<br />
			Final Price : ${sol.finalPrice}<br />
			End Time : ${sol.getEndDateFormat()}<br/>
			<img id="AccountImg" src="${sol.image}"></br>
								<c:choose>
						<c:when test="${sol.reviewed == 'reviewed'}">
						</c:when>
						<c:when test="${sol.reviewed == 'seller'}">
						</c:when>
						<c:otherwise>
							<a href="goToPlaceReviewSold?id=${sol.listingId}">Review</a>
						</c:otherwise>
					</c:choose>
			<hr />
		</c:forEach>
		</hr>
		
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