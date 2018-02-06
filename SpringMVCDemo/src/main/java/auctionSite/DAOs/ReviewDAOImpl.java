package auctionSite.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import auctionSite.controllers.Logging;
import auctionSite.entities.Review;
import auctionSite.entities.User;

public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
	private EntityManagerFactory factory;

	public ReviewDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public ReviewDAOImpl() {	}

	public void placeReview(Review review) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(review);
		Logging.Log("info", review.getReviewId()+" was added");
		manager.getTransaction().commit();
		
	}
	
	public List<Review> getListOfReviewsForOldListing(int id) {
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Review> query = manager.createQuery("select review from Review review where review.oldListing.listingId = ?", Review.class);
		query.setParameter(1, id);
		List<Review> reviews = query.getResultList();
		Logging.Log("info", "Get reviews for "+id+" was done and "+reviews.size()+" reviews were found.");
		return reviews;
	}

	
	public void updateRating(User user, int newRating) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		user.setRating(newRating);
		manager.merge(user);
		Logging.Log("info", user.getUsername()+"'s information was updated.");
		manager.getTransaction().commit();
		System.out.println(user+" "+newRating);
		
	}
	

}
