<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/lw-style.css" type="text/css" />
<title>LITTLE man AUCTIONS</title>



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
		<br>
		<div id="ScrollPic">
			<h2>Recommended auctions ending soon:</h2>
			<marquee>
				<img id="listingPic" src="img/bicycle.jpg"> <img id="listingPic"
					src="img/barbie.jpg"> <img id="listingPic" src="img/PS4.jpg"> <img
					id="listingPic" src="img/XBOX.jpg"> <img id="listingPic" src="img/t-shirt.jpg">
				<img id="listingPic" src="img/afterShave.jpg">
			</marquee>
		</div>
						<h3>${NoListings}</h3>
		<div id="SearSell" class="row show">
			<div id="Searchb" class="col col6">
				<h2>Search</h2>
				<form method="post" action="doSearch">
					<div id="Sear" class="col col4">


						<ul>
							<li class="vertl">${NoListings}</li>
							<li class="vertl">Search :</li></br></br>
							<li id="SBar" class="vertl"><input id="logHom"type="text" name="search"
								placeholder="Search item" /></li></br></br>
							<li class="vertl">Category :</li></br></br>
							<li class="vertl"><select id="logHom" name="category">
									<option value=" ">Select Category</option>
									<option value="Toy">Toy</option>
									<option value="Electronics">Electronics</option>
									<option value="Household">Household</option>
									<option value="Clothing">Clothing</option>
									<option value="Sports">Sports</option>
									<option value="Garden">Garden</option>
									<option value="Diy">Diy</option>
									<option value="Vehicle">Vehicle</option>
									<option value="Toiletries">Toiletries</option>
							</select></li></br></br>
							<li class="vertl">Brand :</li></br></br>
							<li class="vertl"><input id="logHom" type="text" name="brand" /></li>
						</ul>
					</div>
					<div id="Sear" class="col col4">
						<ul>
							<li class="vertl">Size :</li></br></br>
							<li class="vertl"><input id="logHom" type="text" name="size" /></li></br></br>

							<li class="vertl">Colour :</li></br></br>
							<li class="vertl"><input id="logHom" type="text" name="colour" /></li></br></br>
							<li class="vertl">Condition :</li></br></br>
							<li class="vertl"><select  id="logHom" name="condition">
									<option value=" ">Select Condition</option>
									<option value="New">New</option>
									<option value="Used">Used</option>
									<option value="Broken">Broken</option>
									<option value="Scrap">Scrap</option>
							</select></li>
						</ul>
					</div>
					<div id="Sear" class="col col4 last">
						<ul>

							<li class="vertl">Key Words :</li></br></br>
							<li class="vertl"><input id="logHom" type="text" name="keyWords" /></li></br></br>
							<li class="vertl"><input id="logHom" type="submit" value="Search!" /></li>
						</ul>
					</div>
				</form>
			</div>
			<div id="Sellb" class="col col6 last">
				<h2>Sell</h2> <br>
				${priceInvalid}<br>
				<sf:form method="post" action="doListItem" modelAttribute="listing">
					<div id="SellList" class="col col4">
						<ul>
							<li class="vertl">Enter title here:</li>
							<li class="vertl"><sf:input required="1" type="text"
									path="name" placeholder="Enter title" /></li>
							<li class="vertl">Enter Category:</li>
							<li class="vertl"><sf:select path="category">
									<sf:option value="Toy">Toy</sf:option>
									<sf:option value="Electronics">Electronics</sf:option>
									<sf:option value="Household"> Household</sf:option>
									<sf:option value="Clothing">Clothing</sf:option>
									<sf:option value="Sports">Sports</sf:option>
									<sf:option value="Garden">Garden</sf:option>
									<sf:option value="Diy">Diy</sf:option>
									<sf:option value="Vehicle">Vehicle</sf:option>
									<sf:option value="Toiletries">Toiletries</sf:option>
								</sf:select></li>
							<li class="vertl">Enter Brand:</li>
							<li class="vertl"><sf:input required="1" type="text"
									path="brand" placeholder="Enter Brand" /></li>
							<li class="vertl">Enter Condition:</li>
							<li class="vertl"><sf:select path="condition">
									<sf:option value="New">New</sf:option>
									<sf:option value="Used">Used</sf:option>
									<sf:option value="Broken"> Broken</sf:option>
									<sf:option value="Scrap">Scrap</sf:option>
								</sf:select></li>
						</ul>
					</div>
					<div id="SellList" class="col col4">
						<ul>
							<li class="vertl">Enter Size:</li>
							<li class="vertl"><sf:input required="1" type="text"
									path="size" placeholder="Enter size" /></li>
							<li class="vertl">Enter Colour:</li>
							<li class="vertl"><sf:input required="1" type="text"
									path="colour" placeholder="Enter colour" /></li>
							<li class="vertl">Enter Description:</li>
							<li class="vertl"><sf:input required="1" type="text"
									path="description" placeholder="Enter description" /></li>
							<li class="vertl">Enter Some Key Words:</li>
							<li class="vertl"><sf:input required="1" type="text"
									path="keywords" placeholder="Enter keywords" /></li>

						</ul>
					</div>
					<div id="SellList" class="col col4 last">
						<ul>
							<li class="vertl">Enter Starting Price:</li>
							<li class="vertl"><sf:input required="1" type="number" min="1.0"
									path="currentPrice" placeholder="Starting price" /></li>
							<li class="vertl">Enter Buy Now Price:</li>
							<li class="vertl"><sf:input required="1" type="number" min="0.0"
									path="buyNow" placeholder="Buy Now Price" /></li>
							<li class="vertl">Enter Delivery:</li>
							<li class="vertl"><sf:select path="deliveryOptions">
									<sf:option value="Delivery">Delivery</sf:option>
									<sf:option value="Collection">Collection</sf:option>
									<sf:option value="Signed Delivery">Signed Delivery</sf:option>
								</sf:select></li>
							<li class="vertl">Enter Image URL:</li>
							<li class="vertl"><sf:input required="1" type="text"
									path="image" placeholder="Image URL" /></li>
							<li class="vertl"><input type="submit" value="Sell!" /></li>

						</ul>

					</div>
				</sf:form>
			</div>
		</div>
		<div id="Searchmob" class="hidden">
			<h2>Search</h2>
			<ul>
				<li id="SBar" class="vertl col12"><input type="text"
					name="Search" placeholder="Search item"></li>
				<li class="vertl col12"><select>
						<option value="N/A">Select Brand</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12"><select>
						<option value="N/A">Select Size</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12"><select>
						<option value="N/A">Select Style</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12"><select>
						<option value="N/A">Select Colour</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12"><select>
						<option value="N/A">Select Condition</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12"><select>
						<option value="N/A">Price Range</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12"><select>
						<option value="N/A">Auction Time</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12"><select>
						<option value="N/A">Auction Type</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12"><select>
						<option value="N/A">Delivery Options</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12">
					<button type="button">Search</button>
				</li>
				<h4>Is this what you are looking for?</h4>
				<img id="egimg" src="RCboat.png">
			</ul>
		</div>
		<div id="Sellmob" class="hidden">
			<h2>Sell</h2>
			<ul>
				<li class="vertl col12">Enter title here:</li>
				<li class="vertl col12"><input type="text" name="input"
					placeholder="Enter title"></li>
				<li class="vertl col12">Enter key words:</li>
				<li class="vertl col12"><input type="text" name="input"
					placeholder="Styling Key Words"></li>
				<li class="vertl col12">Enter Brand:</li>
				<li class="vertl col12"><input type="text" name="input"
					placeholder="Brand"></li>
				<li class="vertl col12">Enter Size:</li>
				<li class="vertl col12"><input type="text" name="Search"
					placeholder="Size"></li>
				<li class="vertl col12">Enter Start Price:</li>
				<li class="vertl col12"><input type="text" name="input"
					placeholder="Enter Start Price"></li>
				<li class="vertl col12">Buy Now Price:</li>
				<li class="vertl col12"><input type="text" name="buyNow"
					placeholder="0.0"></li>
				<li class="vertl col12"><select id="select2">
						<option value="N/A">Select Auction Type</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12"><select id="select2">
						<option value="N/A">Select Delivery Options</option>
						<option value="Hollister">Hollister</option>
						<option value="Mercedes">Mercedes</option>
						<option value="Audi">Audi</option>
				</select></li>
				<li class="vertl col12">
					<button type="button">Upload Image</button>
				</li>
				<li class="vertl col12"><img id="prev" src="preview.png">
				</li>
				<li class="vertl col12">
					<button id="sell" type="button">Sell</button>
				</li>
			</ul>
		</div>

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