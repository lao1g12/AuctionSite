package auctionSite.DAOs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import auctionSite.entities.Listing;
import auctionSite.entities.OldListing;

public class ControllerSpareLogic {

	public ControllerSpareLogic() {
	}

	public List<Listing> searchForListings(List<Listing> listingList, List<String> listOfKeyWords, int listSize) {

		ArrayList<Listing> searchList = new ArrayList<Listing>();
		for (Listing listing : listingList) {
			int count = 0;
			for (String keyword : listOfKeyWords) {
				if (listing.getListOfWords().contains(keyword)) {
					count += 1;
					if (count == listSize || count == 7) {
						searchList.add(listing);
						break;
					}
				}

			}
		}

		return searchList;

	}

	public OldListing newOldListing(Listing listing) {
		ListingDAOImpl listDao = new ListingDAOImpl();
		OldListing oldListing = new OldListing();
		oldListing.setFinalPrice(listing.getCurrentPrice());
		oldListing.setDeliveryOptions(listing.getDeliveryOptions());
		oldListing.setDescription(listing.getDescription());
		oldListing.setImage(listing.getImage());
		oldListing.setName(listing.getName());
		oldListing.setUser(listing.getUser());
		oldListing.setWinningUser(listing.getWinningUser());
		oldListing.setEndDate(Calendar.getInstance());
		oldListing.setPaid("notPaid");
		oldListing.setReviewed("notReviewed");
		return oldListing;
	}

}
