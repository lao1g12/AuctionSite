package com.fdmgroup.springmvcdemo.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.AuctionSite.Listing;
import com.fdmgroup.AuctionSite.OldListing;
import com.fdmgroup.AuctionSite.Person;
import com.fdmgroup.AuctionSite.User;
import com.fdmgroup.AuctionSite.DAOs.ListingDAOImpl;
import com.fdmgroup.AuctionSite.DAOs.UserMethodDAOImpl;

public class AccountControllerTest {
	private ListingDAOImpl listDao;
	private UserMethodDAOImpl userDao;
	private AccountController aControl;
	private Model model;
	private HttpSession session;
	private Principal principal;
	private HttpServletRequest request;

	
	private Person person;
	private User user;
	private Listing listing;
	private OldListing oldListing;
	private List<Listing> allListings;
	private List<OldListing> allOldListings;
	private Listing listcheck;

	
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
		listing = mock(Listing.class);
		oldListing = mock(OldListing.class);
		aControl = new AccountController(userDao, listDao);
		
	}

	@Test
	public void test_goToUpdateInfo_returnsUserUpdateInfo() {
		assertEquals("user/UpdateInfo", aControl.goToUpdateInfo(model, session));
	}
	
	@Test
	public void test_doUpdateInfo_correct_returnsUserAccount() {
		String sameString = "hello";
		User userCur = mock(User.class);
		User userInf = mock(User.class);
		when(session.getAttribute("user")).thenReturn(userCur);
		when(userCur.getPassword()).thenReturn(sameString);
		when(userInf.getPassword()).thenReturn(sameString);

		assertEquals("user/Account", aControl.doUpdateInfo(userInf, request, session));
	}
	@Test
	public void test_doUpdateInfo_inCorrect_returnsUserAccount() {

		User userCur = mock(User.class);
		User userInf = mock(User.class);
		when(session.getAttribute("user")).thenReturn(userCur);
		when(userCur.getPassword()).thenReturn("hello");
		when(userInf.getPassword()).thenReturn("potato");

		assertEquals("user/UpdateInfo", aControl.doUpdateInfo(userInf, request, session));
	}
	
	@Test
	public void test_doPasswordUpdate_correct__correct_returnsUserAccount() {
		String sameString = "hello";
		String secondSameString = "cheese";
		when(request.getParameter("password")).thenReturn(sameString);
		when(user.getPassword()).thenReturn(sameString);
		when(request.getParameter("newPassword")).thenReturn(secondSameString);
		when(request.getParameter("confNewPassword")).thenReturn(secondSameString);
		when(session.getAttribute("user")).thenReturn(user);
		assertEquals("user/Account", aControl.doPasswordUpdate(request, session));
		
	}
	
	@Test
	public void test_doPasswordUpdate_correct__incorrect_returnsUserAccount() {
		String sameString = "hello";
		when(request.getParameter("password")).thenReturn(sameString);
		when(user.getPassword()).thenReturn(sameString);
		when(request.getParameter("newPassword")).thenReturn("appl");
		when(request.getParameter("confNewPassword")).thenReturn("apple");
		when(session.getAttribute("user")).thenReturn(user);
		assertEquals("user/UpdateInfo", aControl.doPasswordUpdate(request, session));
		
	}
	
	@Test
	public void test_doPasswordUpdate_incorrect_returnsUserAccount() {
		String sameString = "hello";
		when(request.getParameter("password")).thenReturn(sameString);
		when(user.getPassword()).thenReturn("cheese");
		when(session.getAttribute("user")).thenReturn(user);
		assertEquals("user/UpdateInfo", aControl.doPasswordUpdate(request, session));
		
	}

}
