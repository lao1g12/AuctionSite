package auctionSite.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import auctionSite.DAOs.ControllerSpareLogic;
import auctionSite.DAOs.ListingDAOImpl;
import auctionSite.controllers.SearchController;
import auctionSite.entities.Listing;
import auctionSite.entities.User;

public class SearchControllerTest {
	private ListingDAOImpl listDao;
	private SearchController sControl;
	private Model model;
	private HttpSession session;
	private HttpServletRequest request;
	private ControllerSpareLogic spareLogic;

	private User user;

	@Before
	public void setup() {
		request = mock(HttpServletRequest.class);
		session = mock(HttpSession.class);
		model = mock(Model.class);
		listDao = mock(ListingDAOImpl.class);
//		ControllerSpareLogic spareLogic = new ControllerSpareLogic();
		spareLogic = mock(ControllerSpareLogic.class);
		user = mock(User.class);
		sControl = new SearchController(listDao, spareLogic);

	}

	@Test
	public void test_doSearch_listFound_ReturnsSearch() {
		ControllerSpareLogic spareLogic = new ControllerSpareLogic();
		sControl = new SearchController(listDao, spareLogic);
		List<Listing> listingList = new ArrayList<Listing>();
		Listing listing = new Listing("hello", "hello", 12.0, "hello", "hello", "hello", "apple", "banana", "banana",
				"banana", "banana", user);
		Listing listing2 = new Listing("hello", "hello", 12.0, "hello", "apple", "apple", "apple", "banana", "banana",
				"banana", "banana", user);
		listingList.add(listing);
		listingList.add(listing2);
		when(listDao.getAllListings()).thenReturn(listingList);
		when(request.getParameter("keyWords")).thenReturn("hello");
		assertEquals("Search", sControl.doSearch(request));

	}

	@Test
	public void test_doSearch_noListFound_ReturnsSearchRedirectGoHome() {
		when(request.getParameter("keyWords")).thenReturn("hello");
		assertEquals("redirect:/user/goHome", sControl.doSearch(request));

	}

	@Test
	public void test_findListing_returnListing() {
		Listing listing = mock(Listing.class);
		when(listDao.getListing(1)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(1);
		assertEquals("Listing", sControl.findListing(request, 1));
	}

	@Test
	public void test_placeBid_bidHigher_ReturnListing() {
		Listing listing = new Listing();
		listing = mock(Listing.class);
		when(listDao.getListing(2)).thenReturn(listing);
		when(listing.getCurrentPrice()).thenReturn(2.0);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getUsername()).thenReturn("liamooo");
		assertEquals("Listing", sControl.placeBid(request, session, 2, 5.0, model));
	}
	
	@Test
	public void test_placeBid_bidAboveBuyNow() {
		Listing listing = new Listing();
		listing = mock(Listing.class);
		when(listDao.getListing(2)).thenReturn(listing);
		when(listing.getCurrentPrice()).thenReturn(5.0);
		when(listing.getBuyNow()).thenReturn(8.0);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getUsername()).thenReturn("liamooo");
		assertEquals("redirect:/user/buyNowWin", sControl.placeBid(request, session, 2, 10.0, model));
	}

	@Test
	public void test_placeBid_bidLower_ReturnListing() {
		Listing listing = new Listing();
		listing = mock(Listing.class);
		when(listDao.getListing(2)).thenReturn(listing);
		when(listing.getCurrentPrice()).thenReturn(2.0);
		when(session.getAttribute("user")).thenReturn(user);
		when(user.getUsername()).thenReturn("liamooo");
		assertEquals("Listing", sControl.placeBid(request, session, 2, 1.0, model));
	}
	
	@Test
	public void test_buyNow_redirectToBuyNowWin() {
		Listing listing = new Listing();
		listing = mock(Listing.class);
		when(session.getAttribute("user")).thenReturn(user);
		when(listDao.getListing(2)).thenReturn(listing);
		when(listing.getBuyNow()).thenReturn(10.0);
		assertEquals("redirect:/user/buyNowWin", sControl.buyNow(session, 2));
	}
	
	@Test
	public void test_buyNowWin_redirectUserAccount() {
		Listing listing = new Listing();
		listing = mock(Listing.class);
		when(session.getAttribute("winListing")).thenReturn(listing);
		assertEquals("redirect:/user/account", sControl.buyNowWin(session));
	}

}
