 package auctionSite.controllers;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import auctionSite.entities.Person;
import auctionSite.entities.User;

@Controller
public class WelcomeController {

	@Autowired
	private UserMethodDAOImpl userDao;
	@Autowired
	private ListingDAOImpl listDao;



	public WelcomeController() {

	}


	@RequestMapping("/")
	public String goToIndex(Model model, HttpSession session) {
		List<Listing> allListings = listDao.getAllListings();	
		session.setAttribute("allListings", allListings);
		for (Listing listcheck : allListings) {
//			Calendar current = (Calendar) listcheck.getEndDate().clone();
//			current.roll(Calendar.DAY_OF_MONTH, -9);
//			if (Calendar.getInstance().after(current)) {
			if (Calendar.getInstance().after(listcheck.getEndDate())) {
				OldListing oldListing = new OldListing();
				oldListing.setFinalPrice(listcheck.getCurrentPrice());
				oldListing.setDeliveryOptions(listcheck.getDeliveryOptions());
				oldListing.setDescription(listcheck.getDescription());
				oldListing.setImage(listcheck.getImage());
				oldListing.setName(listcheck.getName());
				oldListing.setUser(listcheck.getUser());
				oldListing.setWinningUser(listcheck.getWinningUser());
				oldListing.setEndDate(listcheck.getEndDate());
				oldListing.setPaid("notPaid");
				oldListing.setReviewed("notReviewed");
				Logging.Log("info", listcheck.getListingId() +" is finished and moved to the finished table");
				listDao.AddOldListing(oldListing);
				listDao.removeListing(listcheck.getListingId());

			}
		}

			Listing listing = new Listing();
			model.addAttribute("listing", listing);
			Logging.Log("info", "An unregistered user returned to the homepage");
			return "AuctionHome";

	}
	
	
	@RequestMapping(value = { "/user/goHome", "/goHome"})
	public String goHome(Principal principal, HttpSession session, Model model){
		try{
			String username = principal.getName();
			User user = userDao.getUser(principal.getName());
			session.setAttribute("user", user);
			session.setAttribute("username", principal.getName());
			Person person = user.getPerson();
			session.setAttribute("person", person);
			Listing listing = new Listing();
			model.addAttribute("listing", listing);
			Logging.Log("info", username +" returned to the homepage");
			return "user/LoggedHome";
			}catch (NullPointerException ne){}
		return "redirect:/";
	}

		
		
	@RequestMapping("/signup")
	public String goToRegister(Model model) {
		User user = new User();
		Person person = new Person();
		user.setPerson(person);
		model.addAttribute("user", user);
		return "Register";
	}

	public WelcomeController(UserMethodDAOImpl userDao, ListingDAOImpl listDao) {
		super();
		this.userDao = userDao;
		this.listDao = listDao;
	}


	@RequestMapping("/doRegister")
	public String doRegister(User user, HttpServletRequest request) throws PersistenceException {
		if (user.getPassword().equals(user.getConfirmPassword())) {
			try {
				userDao.signUp(user);
				
			} catch (PersistenceException pe) {
				request.setAttribute("UsernameTaken", "The username you have entered is already taken!");
				Logging.Log("info", "User tried to sign up with user '"+user.getUsername()+"' which is taken, redirected to Register page");
				return "Register";
			}
			Logging.Log("info", user.getUsername()+" Registered to the webpage");
		} else {
			request.setAttribute("Incorrect", "Oops the two passwords entered do not match!");
			Logging.Log("info", "User passwords did not match in Signup, redirected to Register page");
			return "Register";

		}

		return "AuctionHome";
	}

	// Do this for login

	@RequestMapping("/user/login")
	public String goToHome(HttpSession session, Principal principal, Model model) {
		User user = userDao.getUser(principal.getName());
		session.setAttribute("user", user);
		session.setAttribute("username", principal.getName());
		Person person = user.getPerson();
		session.setAttribute("person", person);
		Listing listing = new Listing();
		model.addAttribute("listing", listing);
		Logging.Log("info", user.getUsername()+" logged in");
		return "user/LoggedHome";

	}

	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Logging.Log("info", user.getUsername()+" logged out");
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/user/doListItem")
	public String doListing(Listing listing, HttpServletRequest request, HttpSession session, RedirectAttributes ra)
			throws PersistenceException {

		User user = (User) session.getAttribute("user");
		if(listing.getCurrentPrice() >= listing.getBuyNow()){
				if(listing.getBuyNow() == 0.0) {
					
				} else {
					ra.addFlashAttribute("priceInvalid", "The buy now price must be greater than the starting price!");
					return "redirect:/user/login";
				}
		}
		listing.setUser(user);
		listDao.listListing(listing);
		request.setAttribute("listing", listing);
		Logging.Log("info", user.getUsername()+" listed item "+listing.getListingId());
		return "Listing";
	}

	@RequestMapping("/user/account")
	public String account(HttpSession session) {

		String currentUsername = (String) session.getAttribute("username");
		List<Listing> userListings = listDao.getListOfListingsForUser(currentUsername);
		List<Listing> winningListings = listDao.getListOfListingsForUserWinning(currentUsername);
		List<OldListing> wonListings = listDao.getOldListingsForUser(currentUsername);
		List<OldListing> soldListings = listDao.getOldListingsForSold(currentUsername);
		session.setAttribute("userListings", userListings);
		session.setAttribute("winningListings", winningListings);
		session.setAttribute("wonListings", wonListings);
		session.setAttribute("soldListings", soldListings);
		Logging.Log("info", currentUsername+" viewed accounts page");
		return "user/Account";
	}

}
