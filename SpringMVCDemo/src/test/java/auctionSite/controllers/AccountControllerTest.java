package auctionSite.controllers;

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

import auctionSite.DAOs.ListingDAOImpl;
import auctionSite.DAOs.UserMethodDAOImpl;
import auctionSite.controllers.AccountController;
import auctionSite.entities.Listing;
import auctionSite.entities.OldListing;
import auctionSite.entities.Person;
import auctionSite.entities.User;

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

		assertEquals("redirect:/user/account", aControl.doUpdateInfo(userInf, request, session));
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
	@Test
	public void test_pay_returnsAccountPage() {
		when(listDao.getOldListing(1)).thenReturn(oldListing);
		assertEquals("redirect:/user/account", aControl.pay(1));
	}

}
