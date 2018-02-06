package com.fdmgroup.AuctionSite;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "AUCTION_OLDLISTINGS")
public class OldListing {

	@Id
	@SequenceGenerator(name = "listingid_sequence", sequenceName = "listingid_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listingid_sequence")
	private int listingId;
	private String name;
	private double finalPrice;
	private String description;
	private String deliveryOptions;
	private String image;
	private String paid;
	private String reviewed;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private User user;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private User winningUser;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="oldListing", orphanRemoval=true)
	private Set<Review> reviews=new HashSet<Review>();





	public OldListing() {
	}

	
	
	
	
	
	
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public String getReviewed() {
		return reviewed;
	}


	public void setReviewed(String reviewed) {
		this.reviewed = reviewed;
	}


	public String getPaid() {
		return paid;
	}



	public void setPaid(String paid) {
		this.paid = paid;
	}



	public User getWinningUser() {
		return winningUser;
	}

	public void setWinningUser(User winningUser) {
		this.winningUser = winningUser;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getListingId() {
		return listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeliveryOptions() {
		return deliveryOptions;
	}

	public void setDeliveryOptions(String deliveryOptions) {
		this.deliveryOptions = deliveryOptions;
	}


	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getEndDateFormat() {

		Date date = this.endDate.getTime();

		return date;

	}


}
