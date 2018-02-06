package com.fdmgroup.springmvcdemo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.AuctionSite.OldListing;
import com.fdmgroup.AuctionSite.Review;
import com.fdmgroup.AuctionSite.User;
import com.fdmgroup.AuctionSite.DAOs.ListingDAOImpl;
import com.fdmgroup.AuctionSite.DAOs.ReviewDAOImpl;
import com.fdmgroup.AuctionSite.DAOs.UserMethodDAOImpl;

@Controller
public class ReviewController {

	public ReviewController() {	}
	@Autowired
	private UserMethodDAOImpl userDao;
	@Autowired
	private ListingDAOImpl listDao;
	@Autowired
	private ReviewDAOImpl reviewDao;
	
	public ReviewController(UserMethodDAOImpl userDao, ListingDAOImpl listDao, ReviewDAOImpl reviewDao) {
		super();
		this.userDao = userDao;
		this.listDao = listDao;
		this.reviewDao = reviewDao;
	}
	

	@RequestMapping("/user/goToPlaceReviewBought")
	public String goToPlaceReviewBought(Model model, @RequestParam int id) {
		OldListing oldListing = listDao.getOldListing(id);
		model.addAttribute("role", "buyer");
		model.addAttribute("oldListing", oldListing);
		return "user/Review";
		
	}
	
	@RequestMapping("/user/goToPlaceReviewSold")
	public String goToPlaceReviewSold(Model model, HttpServletRequest request, @RequestParam int id) {
		OldListing oldListing = listDao.getOldListing(id);
		model.addAttribute("role", "seller");
		model.addAttribute("oldListing", oldListing);
		return "user/Review";
		
	}
	
	@RequestMapping("/user/doReview")
	public String doReview(HttpServletRequest request, HttpSession session) {
		User seller = userDao.getUser(request.getParameter("seller"));
		User buyer = userDao.getUser(request.getParameter("buyer"));
		OldListing oldListing = listDao.getOldListing(Integer.parseInt(request.getParameter("oldListing")));
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int rating = Integer.parseInt(request.getParameter("rating"));
		String reviewer = request.getParameter("reviewer");
		Review review = new Review(title, body, rating, oldListing, seller, buyer);
		User user = (User) session.getAttribute("user");
		String username = user.getUsername();
		System.out.println("111111111111111111111111111111111111111111111111111111111111111111");
		if(username.equals(seller.getUsername())) {
			System.out.println("222222222222222222222222222222222222222222222222222222222222222222222222222222");
			int newRating =buyer.getRating()+rating;
			reviewDao.updateRating(buyer, newRating);
			System.out.println(newRating);
		} else {
			System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333333");
			int newRating = seller.getRating()+rating;
			System.out.println(newRating);
			reviewDao.updateRating(seller, newRating);
		}
		reviewDao.placeReview(review);
		if(oldListing.getReviewed().equals("notReviewed")) {
			oldListing.setReviewed(reviewer);
			
		}else {
			oldListing.setReviewed("reviewed");
		}
		listDao.updateOldListing(oldListing);
		return "redirect:/user/account";
	}
}
