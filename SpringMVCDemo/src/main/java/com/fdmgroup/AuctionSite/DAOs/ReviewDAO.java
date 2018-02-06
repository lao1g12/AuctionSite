package com.fdmgroup.AuctionSite.DAOs;

import java.util.List;

import com.fdmgroup.AuctionSite.Review;
import com.fdmgroup.AuctionSite.User;

public interface ReviewDAO {
	
	public void placeReview(Review review);
	
	public void updateRating(User user, int newRating);
	
	public List<Review> getListOfReviewsForOldListing(int id);

}
