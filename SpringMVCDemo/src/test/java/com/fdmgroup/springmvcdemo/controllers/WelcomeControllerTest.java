package com.fdmgroup.springmvcdemo.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.AuctionSite.Listing;
import com.fdmgroup.AuctionSite.OldListing;
import com.fdmgroup.AuctionSite.Person;
import com.fdmgroup.AuctionSite.User;
import com.fdmgroup.AuctionSite.DAOs.ListingDAOImpl;
import com.fdmgroup.AuctionSite.DAOs.UserMethodDAOImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WelcomeControllerTest {
	private ListingDAOImpl listDao;
	private UserMethodDAOImpl userDao;
	private WelcomeController wControl;
	private Model model;
	private HttpSession session;
	private Principal principal;
	private HttpServletRequest request;
	private RedirectAttributes ra;

	
	private Person person;
	private User user;
	private Listing listing;
	private OldListing oldListing;
	private List<Listing> allListings;
	private List<OldListing> allOldListings;

	
	@Before
	public void setup(){
		request=mock(HttpServletRequest.class);
		session=mock(HttpSession.class);
		ra=mock(RedirectAttributes.class);
		model = mock(Model.class);
		listDao = mock(ListingDAOImpl.class);
		userDao = mock(UserMethodDAOImpl.class);
		principal = mock(Principal.class);
		person = mock(Person.class);
		user = mock(User.class);
		listing = mock(Listing.class);
		oldListing = mock(OldListing.class);
		wControl = new WelcomeController(userDao, listDao);
		
		

	}
	
	@Test
	public void test_goHome_UserIsValidThenReturnsLoggedHome(){
		when(principal.getName()).thenReturn("liamooo");
		when(userDao.getUser("liamooo")).thenReturn(user);
		when(user.getPerson()).thenReturn(person);
		wControl.goHome(principal, session, model);
		assertEquals("user/LoggedHome",wControl.goHome(principal, session, model));
	}
	
	@Test
	public void test_goHome_UserIsInvalidThenReturnsRedirect(){
		when(principal.getName()).thenReturn(null);
		wControl.goHome(principal, session, model);
		assertEquals("redirect:/",wControl.goHome(principal, session, model));
	}
	

	
	@Test
	public void test_signUp_returnsRegister(){
		assertEquals("Register", wControl.goToRegister(model));
	}
	
	@Test
	public void test_goToIndex_returnsAuctionHome(){
		assertEquals("AuctionHome", wControl.goToIndex(model, session));
	}
	
	@Test
	public void test_goToIndex_IfStatementTrue_thenListingIsRemoved(){
		List<Listing> listingList = new ArrayList<Listing>();
		Listing listing = new Listing("hello","hello",12.0,"hello","hello","hello","apple","banana","banana","banana","banana", user);
		listing.setListingId(1);
		listingList.add(listing);
		Calendar current = (Calendar) Calendar.getInstance();
		current.roll(Calendar.DAY_OF_MONTH, -9);
		listing.setEndDate(current);
		when(listDao.getAllListings()).thenReturn(listingList);
		wControl.goToIndex(model, session);
		verify(listDao).removeListing(1);
	}

	@Test
	public void test_goToHome_returnsUserLoggedHome(){
		when(principal.getName()).thenReturn("liamooo");
		when(userDao.getUser("liamooo")).thenReturn(user);
		when(user.getPerson()).thenReturn(person);
		assertEquals("user/LoggedHome", wControl.goToHome(session, principal, model));
	}
	
	@Test
	public void test_logOut_callsInvalidate(){
		wControl.logout(session);
		verify(session).invalidate();
	}
	
	@Test
	public void test_logOut_redirects(){
		assertEquals("redirect:/", wControl.logout(session));
	}
	
	@Test
	public void test_doListing_returnsListing(){
		assertEquals("Listing", wControl.doListing(listing, request, session, ra));
	}
	
	@Test
	public void test_account_returnsUserAccount(){

		when(session.getAttribute("username")).thenReturn("liamooo");
		when(listDao.getListOfListingsForUser("liamooo")).thenReturn(allListings);
		when(listDao.getListOfListingsForUserWinning("liamooo")).thenReturn(allListings);
		assertEquals("user/Account", wControl.account(session));
	}
	
	@Test
	public void test_doRegister_(){
		String sameString = "hello";
		when(user.getPassword()).thenReturn(sameString);
		when(user.getConfirmPassword()).thenReturn(sameString);
		assertEquals("AuctionHome", wControl.doRegister(user, request));
		
	}
	@Test
	public void test_doRegister_2(){
		
		when(user.getPassword()).thenReturn("cheese");
		when(user.getConfirmPassword()).thenReturn("potato");
		
		assertEquals("Register", wControl.doRegister(user, request));
		
	}
}
