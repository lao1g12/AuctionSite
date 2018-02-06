package com.fdmgroup.springmvcdemo.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import auctionSite.DAOs.ListingDAOImpl;
import auctionSite.DAOs.UserMethodDAOImpl;
import auctionSite.controllers.SearchController;
import auctionSite.entities.Listing;
import auctionSite.entities.OldListing;
import auctionSite.entities.Person;
import auctionSite.entities.SearchMethod;
import auctionSite.entities.User;

public class SearchControllerTest {
	private ListingDAOImpl listDao;
	private UserMethodDAOImpl userDao;
	private SearchController sControl;
	private Model model;
	private HttpSession session;
	private Principal principal;
	private HttpServletRequest request;
	private SearchMethod sm;
	private int listSize;
	

	
	private Person person;
	private User user;
	private OldListing oldListing;
	private List<OldListing> allOldListings;
	private Listing listcheck;
	private List<String> listOfKeyWords;


	
	@Before
	public void setup(){
		request=mock(HttpServletRequest.class);
		session=mock(HttpSession.class);
		model = mock(Model.class);
		listDao = mock(ListingDAOImpl.class);
		userDao = mock(UserMethodDAOImpl.class);
		principal = mock(Principal.class);
		person = mock(Person.class);
		user = mock(User.class);
		oldListing = mock(OldListing.class);
		sControl = new SearchController(userDao, listDao);



		
	}


	@Test 
	public void test_doSearch_listFound_ReturnsSearch() {
		List<Listing> listingList = new ArrayList<Listing>();
		Listing listing = new Listing("hello","hello",12.0,"hello","hello","hello","apple","banana","banana","banana","banana", user);
		Listing listing2 = new Listing("hello","hello",12.0,"hello","apple","apple","apple","banana","banana","banana","banana", user);
		listingList.add(listing);
		listingList.add(listing2);
		when(listDao.getAllListings()).thenReturn(listingList);
		when(request.getParameter("keyWords")).thenReturn("hello");
		assertEquals("Search", sControl.doSearch(request, session));
		
	}
	@Test 
	public void test_doSearch_noListFound_ReturnsSearchRedirectGoHome() {
		when(request.getParameter("keyWords")).thenReturn("hello");
		assertEquals("redirect:/user/goHome", sControl.doSearch(request, session));
		
	}
	
	@Test
	public void test_findListing_returnListing(){
		assertEquals("Listing", sControl.findListing(request, 1));
	}
	
	@Test
	public void test_placeBid_bidHigher_ReturnListing(){
		Listing listing = new Listing();
		listing = mock(Listing.class);
		when(listDao.getListing(2)).thenReturn(listing);
		when(listing.getCurrentPrice()).thenReturn(2.0);
		assertEquals("Listing", sControl.placeBid(request, session, 2, 5.0, model));
	}
	
	@Test
	public void test_placeBid_bidLower_ReturnListing(){
		Listing listing = new Listing();
		listing = mock(Listing.class);
		when(listDao.getListing(2)).thenReturn(listing);
		when(listing.getCurrentPrice()).thenReturn(2.0);
		assertEquals("Listing", sControl.placeBid(request, session, 2, 1.0, model));
	}


}
