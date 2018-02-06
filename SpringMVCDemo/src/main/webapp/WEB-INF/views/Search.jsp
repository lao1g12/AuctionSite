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
	<h2>Items Found:</h2>
	</rl>
		<c:forEach items="${searchList}" var="sl">
			Listing title : <a href="getListing?id=${sl.listingId}">${sl.name}</a><br />
			Listing ID : ${sl.listingId}<br />
			Listing description : ${sl.description}<br />
			Current Price : ${sl.currentPrice}<br />
			<img id="AccountImg" src="${sl.image}">
			<hr />
		</c:forEach>
		
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