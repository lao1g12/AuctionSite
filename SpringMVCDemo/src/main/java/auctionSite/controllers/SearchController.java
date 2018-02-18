package auctionSite.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import auctionSite.DAOs.ControllerSpareLogic;
import auctionSite.DAOs.ListingDAOImpl;
import auctionSite.entities.Listing;
import auctionSite.entities.Logging;
import auctionSite.entities.User;

@Controller
public class SearchController {

	public SearchController() {
	}

	@Autowired
	private ListingDAOImpl listDao;
	@Autowired
	private ControllerSpareLogic spareLogic;

	public SearchController(ListingDAOImpl listDao, ControllerSpareLogic spareLogic) {
		super();
		this.listDao = listDao;
		this.spareLogic = spareLogic;
	}

	@RequestMapping(value = { "/user/doSearch", "/doSearch" })
	public String doSearch(HttpServletRequest request, HttpSession session) {
		StringBuffer sb = new StringBuffer();
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
		List<Listing> listingList = listDao.getAllListings();
		int listSize = listOfKeyWords.size();
		for (int i = 0; i < listOfKeyWords.size(); i++) {
			listSize = listOfKeyWords.size() - i;
			List<Listing> searchList = spareLogic.searchForListings(listingList, listOfKeyWords, listSize);
			if (searchList.size() > 0) {
				request.setAttribute("searchList", searchList);
				Logging.Log("info", "A search was made and " + searchList.size() + " listings were found");
				return "Search";
			}
		}
		Logging.Log("info", "A search was made and no listings were found.");
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
			if (listing.getBuyNow() > 0) {
				if (bid >= listing.getBuyNow()) {
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
	public String buyNow(HttpSession session, @RequestParam int id) {
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
		spareLogic.newOldListing(listing);
		return "redirect:/user/account";

	}

}
