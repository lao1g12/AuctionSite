package auctionSite.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import auctionSite.DAOs.ListingDAOImpl;
import auctionSite.DAOs.UserMethodDAOImpl;
import auctionSite.entities.Listing;
import auctionSite.entities.OldListing;
import auctionSite.entities.SearchMethod;
import auctionSite.entities.User;

@Controller
public class SearchController {

	public SearchController() {
	}

	@Autowired
	private UserMethodDAOImpl userDao;
	@Autowired
	private ListingDAOImpl listDao;

	public SearchController(UserMethodDAOImpl userDao, ListingDAOImpl listDao) {
		super();
		this.userDao = userDao;
		this.listDao = listDao;
	}

	@RequestMapping(value = { "/user/doSearch", "/doSearch" })
	public String doSearch(HttpServletRequest request, HttpSession session) {
		StringBuffer sb = new StringBuffer();
		SearchMethod sm = new SearchMethod();
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		String brand = request.getParameter("brand");
		String size = request.getParameter("size");
		String colour = request.getParameter("colour");
		String condition = request.getParameter("condition");
		String keyWords = request.getParameter("keyWords");

		sb.append(search);
		sb.append(" " + category);
		sb.append(" " + brand);
		sb.append(" " + size);
		sb.append(" " + colour);
		sb.append(" " + condition);
		sb.append(" " + keyWords);
		String searchString = sb.toString();

		List<String> listOfKeyWords = new ArrayList<String>(Arrays.asList(searchString.split(" ")));
		System.out.println(listOfKeyWords);
		List<Listing> listingList = listDao.getAllListings();
		System.out.println(listingList.size());
		int listSize = listOfKeyWords.size();
		System.out.println(listOfKeyWords.size());
		for (int i = 0; i < listOfKeyWords.size(); i++) {
			listSize = listOfKeyWords.size() - i;
			List<Listing> searchList = sm.searchForListings(listingList, listOfKeyWords, listSize);
			if (searchList.size() > 0) {
				request.setAttribute("searchList", searchList);
				Logging.Log("info", "A search was made and " + searchList.size() + " listings were found");

				return "Search";
			}
		}
		Logging.Log("info", "A search was made and no listings were found.");
			String username = (String) session.getAttribute("username");
			return ("redirect:/user/goHome");


	}

	@RequestMapping(value = { "/getListing", "/user/getListing" })
	public String findListing(HttpServletRequest request, @RequestParam int id) {
		Listing listing = listDao.getListing(id);
		request.setAttribute("listing", listing);
		Logging.Log("info", listing.getListingId() + " was viewed");
		return "Listing";

	}

	@RequestMapping("/user/placeBid")
	public String placeBid(HttpServletRequest request, HttpSession session, @RequestParam int id,
			@RequestParam double bid, Model model) {
		User user = (User) session.getAttribute("user");
		Listing listing = listDao.getListing(id);
		if (bid > listing.getCurrentPrice()) {
			if(listing.getBuyNow() > 0) {
				if(bid >= listing.getBuyNow()) {
					listing.setCurrentPrice(listing.getBuyNow());
					listing.setWinningUser(user);
					session.setAttribute("winListing", listing);
					return "redirect:/user/buyNowWin";
				} 
			}
				listDao.bidOnListing(id, bid, user);
				Listing newListing = listDao.getListing(id);
				request.setAttribute("listing", newListing);
				request.setAttribute("Bid", "Your bid has successfully been placed!");
				Logging.Log("info", user.getUsername() + " placed a bid of " + bid + " on " + listing.getListingId());
				return "Listing";
			
		} else
			request.setAttribute("listing", listing);
		request.setAttribute("Bid", "Your bid was too low!");
		Logging.Log("info", user.getUsername() + " placed a bid of " + bid + " on " + listing.getListingId()
				+ ", but the bid was too low, redirected too the Listing page");
		return "Listing";
	}
	
	@RequestMapping("/user/buyNow")
	public String buyNow(HttpServletRequest request, HttpSession session, @RequestParam int id,
			Model model, RedirectAttributes ra) {
		User user = (User) session.getAttribute("user");
		Listing listing = listDao.getListing(id);
		listing.setCurrentPrice(listing.getBuyNow());
		listing.setWinningUser(user);
		session.setAttribute("winListing", listing);
		return "redirect:/user/buyNowWin";
		
	}
	
	@RequestMapping("/user/buyNowWin")
	public String buyNowWin(HttpSession session) {
		Listing listing = (Listing) session.getAttribute("winListing");
		OldListing oldListing = new OldListing();
		oldListing.setFinalPrice(listing.getCurrentPrice());
		oldListing.setDeliveryOptions(listing.getDeliveryOptions());
		oldListing.setDescription(listing.getDescription());
		oldListing.setImage(listing.getImage());
		oldListing.setName(listing.getName());
		oldListing.setUser(listing.getUser());
		oldListing.setWinningUser(listing.getWinningUser());
		oldListing.setEndDate(Calendar.getInstance());
		oldListing.setPaid("notPaid");
		oldListing.setReviewed("notReviewed");
		Logging.Log("info", listing.getListingId() +" is finished and moved to the finished table");
		listDao.AddOldListing(oldListing);
		listDao.removeListing(listing.getListingId());
		
		
		return "redirect:/user/account";
		
	}

}
