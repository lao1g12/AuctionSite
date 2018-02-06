package auctionSite.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "AUCTION_LISTINGS")
public class Listing {

	@Id
	@SequenceGenerator(name = "listingid_sequence", sequenceName = "listingid_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listingid_sequence")
	private int listingId;
	private String name;
	private String keywords;
	private double currentPrice;
	private double buyNow;
	private String description;
	private String brand;
	private String category;
	@Column(name = "ITEM_SIZE")
	private String size;
	private String colour;
	private String condition;
	private String deliveryOptions;
	private String image;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startDate = Calendar.getInstance();
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private User user;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private User winningUser;
	@Transient
	private String listOfWords;




	public Listing() {
	}

	public Listing(String name, String keywords, double currentPrice, String description, String brand, String category,
			String size, String colour, String condition, String image, String deliveryOptions, User user) {
		super();
		this.name = name;
		this.keywords = keywords;
		this.currentPrice = currentPrice;
		this.description = description;
		this.brand = brand;
		this.category = category;
		this.size = size;
		this.colour = colour;
		this.condition = condition;
		this.image = image;
		this.deliveryOptions = deliveryOptions;
		// this.user = user;
		// user.addListing(this);
	}
	
	public String getListOfWords() {
		StringBuffer sb = new StringBuffer();
		sb.append(name+" "+brand+" "+category+" "+colour+" "+condition+" "+description+" "+keywords+" "+size);
		String searchString = sb.toString();
		return searchString;
	}

	public void setListOfWords(String listOfWords) {

		this.listOfWords = listOfWords;
	}
	
	public double getBuyNow() {
		return buyNow;
	}

	public void setBuyNow(double buyNow) {
		this.buyNow = buyNow;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDeliveryOptions() {
		return deliveryOptions;
	}

	public void setDeliveryOptions(String deliveryOptions) {
		this.deliveryOptions = deliveryOptions;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate() {
		this.endDate = (Calendar) startDate.clone();
		this.endDate.add(Calendar.DAY_OF_MONTH, 7);
	}
	
	public void setEndDate(Calendar endDate){
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		user.addListing(this);
	}

	public Date getStartDateFormat() {

		Date date = this.startDate.getTime();

		return date;

	}
	public Date getEndDateFormat() {

		Date date = this.endDate.getTime();

		return date;

	}


}
