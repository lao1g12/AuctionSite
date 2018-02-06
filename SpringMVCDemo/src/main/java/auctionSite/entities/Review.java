package auctionSite.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AUCTION_REVIEW")
public class Review {

	@Id
	@SequenceGenerator(name = "reviewId_sequence", sequenceName = "reviewId_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewId_sequence")
	private int reviewId;
	private String title;
	private String body;
	private int rating;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private OldListing oldListing;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private User seller;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private User buyer;
	
	
	public Review() {	}

	public Review(String title, String body, int rating, OldListing oldListing, User seller, User buyer) {
		super();
		this.title = title;
		this.body = body;
		this.rating = rating;
		this.oldListing = oldListing;
		this.seller = seller;
		this.buyer = buyer;
	}
	

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public OldListing getOldListing() {
		return oldListing;
	}

	public void setOldListing(OldListing oldListing) {
		this.oldListing = oldListing;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
}

