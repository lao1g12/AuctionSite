package auctionSite.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import auctionSite.DAOs.ListingDAOImpl;

public class SearchMethod {
	@Autowired
	private EntityManagerFactory factory;

	
	public List<Listing> searchForListings(List<Listing> listingList, List<String> listOfKeyWords, int listSize){
		
		ArrayList<Listing> searchList = new ArrayList<Listing>();
		for (Listing listing : listingList) {
			int count = 0;
			for (String keyword : listOfKeyWords) {
//				StringBuffer sb = new StringBuffer();
//				sb.append(listing.getName()+" "+listing.getBrand()+" "+listing.getCategory()+" "+listing.getColour()+" "+listing.getCondition()+" "+listing.getDescription()+" "+listing.getKeywords()+" "+listing.getSize());
//				String searchString = sb.toString();
//				if (listing.getName().contains(keyword) || listing.getDescription().contains(keyword) || listing.getKeywords().contains(keyword)) {
				System.out.println(listing.getListOfWords());
				if(listing.getListOfWords().contains(keyword)){
					count += 1;
					System.out.println(listSize);
					System.out.println(count);
					if(count == listSize || count == 7){
						searchList.add(listing);
//						System.out.println(searchList);
						break;
					}
				}
//				} else
//					continue;

			}
			}
		
//		System.out.println(searchList);
		return searchList;
		
	}
	

	

}
