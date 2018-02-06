package auctionSite.DAOs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import auctionSite.controllers.Logging;
import auctionSite.entities.Listing;
import auctionSite.entities.OldListing;
import auctionSite.entities.User;

public class ListingDAOImpl implements ListingDAO {


	@Autowired
	private EntityManagerFactory factory;

	public ListingDAOImpl(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
	public ListingDAOImpl() {	}

	public void listListing(Listing listing) {
		EntityManager manager = factory.createEntityManager();
		listing.setEndDate();
		manager.getTransaction().begin();
		manager.persist(listing);
		Logging.Log("info", listing.getListingId()+" was listed");
		manager.getTransaction().commit();

	}

	public void bidOnListing(int listingId, double bid, User user) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Listing listing = manager.find(Listing.class, listingId);
		if (bid > listing.getCurrentPrice()) {
			listing.setCurrentPrice(bid);
			listing.setWinningUser(user);
			Logging.Log("info", bid+" was placed on "+ listing.getListingId());
			manager.merge(listing);
		} else {
			Logging.Log("error", "Your bid is too low! Please enter a price greater than " + listing.getCurrentPrice());
		}
		manager.getTransaction().commit();

	}

	public Listing getListing(int listingId) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Listing listing = manager.find(Listing.class, listingId);
		manager.getTransaction().commit();
		Logging.Log("info", listing.getListingId()+" was retrieved");
		return listing;
	}

	public List<Listing> getListOfListingsForUser(String username) {
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Listing> query = manager.createQuery("select listing from Listing listing where listing.user.username = ?", Listing.class);
		query.setParameter(1, username);
		List<Listing> listingList = query.getResultList();
		Logging.Log("info", "Get listings for "+username+" was done and "+listingList.size()+" listings were found.");
		return listingList;
	}

	public void updateListing(Listing listing) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(listing);
		Logging.Log("info", listing.getListingId()+" was updated.");
		manager.getTransaction().commit();
		
		
	}

	public void removeListing(int listingId) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Listing listing = manager.find(Listing.class, listingId);
		Logging.Log("info", listing.getListingId()+" was removed.");

		manager.remove(listing);
		manager.getTransaction().commit();
		manager.close();
	}


	public List<Listing> getAllListings() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		TypedQuery<Listing> query = manager.createQuery("select listing from Listing listing", Listing.class);
		List<Listing> listingList = query.getResultList();
		Logging.Log("info", "Get all listings was invoked, "+listingList.size()+" listings were found");
		manager.getTransaction().commit();
		return listingList;
	}

	@Override
	public List<Listing> getListOfListingsForUserWinning(String username) {
		EntityManager manager = factory.createEntityManager();
		TypedQuery<Listing> query = manager.createQuery("select listing from Listing listing where listing.winningUser.username = ?", Listing.class);
		query.setParameter(1, username);
		List<Listing> listingList = query.getResultList();
		Logging.Log("info", "Get all listings which are currently being won by "+username+", "+listingList.size()+" listings were found");
		return listingList;
	}

	@Override
	public void AddOldListing(OldListing oldListing) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(oldListing);
		Logging.Log("info", "This listing has finished and moved to finished table");
		manager.getTransaction().commit();
		
	}

	@Override
	public List<OldListing> getOldListingsForUser(String username) {
		EntityManager manager = factory.createEntityManager();
		TypedQuery<OldListing> query = manager.createQuery("select listing from OldListing listing where listing.winningUser.username = ?", OldListing.class);
		query.setParameter(1, username);
		List<OldListing> listingList = query.getResultList();
		Logging.Log("info", "Get all listings which have been won by "+username+", "+listingList.size()+" listings were found");
		return listingList;
	}

	@Override
	public void updateOldListing(OldListing oldListing) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(oldListing);
		Logging.Log("info", oldListing.getListingId()+" was updated.");
		manager.getTransaction().commit();
		
	}

	@Override
	public OldListing getOldListing(int OldListingId) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		OldListing OldListing = manager.find(OldListing.class, OldListingId);
		manager.getTransaction().commit();
		Logging.Log("info", OldListing.getListingId()+" was retrieved");
		return OldListing;
	}

	@Override
	public List<OldListing> getOldListingsForSold(String username) {
		EntityManager manager = factory.createEntityManager();
		TypedQuery<OldListing> query = manager.createQuery("select oldListing from OldListing oldListing where oldListing.user.username = ?", OldListing.class);
		query.setParameter(1, username);
		List<OldListing> listingList = query.getResultList();
		Logging.Log("info", "Get all listings which have been won by "+username+", "+listingList.size()+" listings were found");
		return listingList;
	}

}
