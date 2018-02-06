package com.fdmgroup.AuctionSite.DAOs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fdmgroup.AuctionSite.Listing;
import com.fdmgroup.AuctionSite.OldListing;
import com.fdmgroup.AuctionSite.User;

public interface ListingDAO {

	public void listListing(Listing listing);
	
	public void updateListing(Listing listing);

	public void bidOnListing(int listingId, double bid, User user);

	public Listing getListing(int listingId);
	
	public List<Listing> getAllListings();
	
	public List<Listing> getListOfListingsForUser(String username);
	
	public List<Listing> getListOfListingsForUserWinning(String username);
	
	public void removeListing(int listingId);
	
	public void AddOldListing(OldListing oldListing);
	
	public List<OldListing> getOldListingsForUser(String username);
	
	public void updateOldListing(OldListing oldListing);
	
	public OldListing getOldListing(int OldListingId);
	
	public List<OldListing> getOldListingsForSold(String username);

}
