package com.fdmgroup.AuctionSite;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.AuctionSite.DAOs.ListingDAOImpl;
import com.fdmgroup.AuctionSite.DAOs.UserMethodDAOImpl;

public class AuctionRunner {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		ListingDAOImpl listDao = new ListingDAOImpl(factory);
		UserMethodDAOImpl useDao = new UserMethodDAOImpl(factory);
		User user = useDao.getUser("liamooo");
//		useDao.signUp(user);
//		useDao.login("Liam.Oliver", "12345");
//		listDao.listListing(listing);
//		listDao.listListing(listing2);
//		listDao.listListing(listing3);
//		System.out.println(listDao.getListOfListingsWhere("Toy Doll Children Apple Cheese"));
//		listDao.bidOnListing(2, 17.00);
//		System.out.println(listDao.getListOfListingsForUser("Darryl.cfhkdljsk"));
//		listDao.updateListing(listing);
//		listDao.removeListing(11);
		System.out.println(user);
		Listing listing = new Listing();
		listing.setUser(user);
		listDao.listListing(listing);
		factory.close();

	}

}
