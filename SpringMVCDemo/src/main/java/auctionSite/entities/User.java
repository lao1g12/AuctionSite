package auctionSite.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="AUCTION_USERS")
public class User {

	@Id
	private String username;
	private String password;
	@Transient
	private String confirmPassword;
	private String role;
	private int rating;
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Person person;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user", orphanRemoval=true)
	private Set<Listing> listings=new HashSet<Listing>();
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user", orphanRemoval=true)
	private Set<Listing> winninglistings=new HashSet<Listing>();
	




	public User() {	}
	
	
	
	
	public User(String username, String password, String confirmPassword) {
		super();
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	
	
	public Set<Listing> getWinninglistings() {
		return winninglistings;
	}

	public void setWinninglistings(Set<Listing> winninglistings) {
		this.winninglistings = winninglistings;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Set<Listing> getListings() {
		return listings;
	}
	public void setListings(Set<Listing> listings) {
		this.listings = listings;
	}


	public void addListing(Listing listing) {
		listings.add(listing);
		
	}
		

}
