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
			<div>
				<ul class="horiz">
					<li class="horizl"><a href="goHome">Home</a></li>
					<li class="horizl"><a href="www.categories.com">Categories</a></li>
					<li class="horizl"><a href="www.suggested.com">Suggested</a></li>
					<li class="horizl"><a href="www.deals.com">Deals</a></li>
					<li class="horizl"><a href="www.shops.com">Shops</a></li>
				</ul>
			</div>

		</div>
<h2>${listing.getName()}</h2>
<img id="listingPic"src="${listing.getImage()}"></br>
Description : ${listing.getDescription()}</br>
End date : ${listing.getEndDateFormat()}</br>
Current Price : ${listing.getCurrentPrice()}</br>
<c:choose>
	<c:when test='${username == null}'>
	
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test='${username == listing.getUser().getUsername()}'>
			
			</c:when>
			<c:otherwise>
				<h2>${Bid}</h2>
				<form method="post" action="placeBid">
					<input type="text" name="bid" placeholder="Enter bid"/></br>
					<input type="hidden" name="id" value="${listing.getListingId()}"/>
					<input type="submit" value="Place Bid"></br>
				</form>
				<c:choose>
				<c:when test='${listing.getBuyNow() > 0}'>
				Buy Now price: ${listing.getBuyNow()} <br>
				<form method="post" action="buyNow">
					<input type="hidden" name="id" value="${listing.getListingId()}"/>
					<input type ="submit" value="Buy Now">
				</form>
				</c:when>
				<c:otherwise>
				</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

<rl/>
<h2>Further Info</h2>
Category : ${listing.getCategory()}</br>
Brand : ${listing.getBrand()}</br>

Key Words : ${listing.getKeywords()}
</br>
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


</body>
</html>