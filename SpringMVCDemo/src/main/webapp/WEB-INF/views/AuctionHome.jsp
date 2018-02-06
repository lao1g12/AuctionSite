<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/lw-style.css" type="text/css" />
<title>LITTLE man AUCTIONS</title>



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
		<div id="ScrollPic">

			<h2>Recommended auctions ending soon:</h2>
				<marquee>
				<img id="rcboat" src="img/RCboat.png"> <img id="barbie"
					src="img/barbie.png"> <img id="ps4" src="ps4.png"> <img
					id="chalk" src="img/chalk.png"> <img id="jumper" src="img/jumper.png">
				<img id="bicycle" src="img/bicycle.png">
			</marquee>
		</div>


			<div id="Searchb" class="col col12 ">
				<h2>Search</h2>
				<form method="post" action="doSearch">
					<div id="Sear" class="col col4">


						<ul>
							<li class="vertl">${NoListings}</li>
							<li class="vertl">Search :</li></br></br>
							<li id="SBar" class="vertl"><input id="searHom"type="text" name="search"
								placeholder="Search item" /></li></br></br>
							<li class="vertl">Category :</li></br></br>
							<li class="vertl"><select id="searHom" name="category">
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
							<li class="vertl"><input id="searHom" type="text" name="brand" /></li>
						</ul>
					</div>
					<div id="Sear" class="col col4">
						<ul>
							<li class="vertl">Size :</li></br></br>
							<li class="vertl"><input id="searHom" type="text" name="size" /></li></br></br>

							<li class="vertl">Colour :</li></br></br>
							<li class="vertl"><input id="searHom" type="text" name="colour" /></li></br></br>
							<li class="vertl">Condition :</li></br></br>
							<li class="vertl"><select  id="searHom" name="condition">
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
							<li class="vertl"><input id="searHom" type="text" name="keyWords" /></li></br></br>
							<li class="vertl"><input id="searHom" type="submit" value="Search!" /></li>
						</ul>
					</div>
				</form>
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
				<li class="vertl col12">Enter Buy Now Price:</li>
				<li class="vertl col12"><input type="text" name="input"
					placeholder="Enter Buy Now Price"></li>
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