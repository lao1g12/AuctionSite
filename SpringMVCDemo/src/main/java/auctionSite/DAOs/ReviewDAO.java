package auctionSite.DAOs;

import java.util.List;

import auctionSite.entities.Review;
import auctionSite.entities.User;

public interface ReviewDAO {
	
	public void placeReview(Review review);
	
	public void updateRating(User user, int newRating);
	
	public List<Review> getListOfReviewsForOldListing(int id);

}
