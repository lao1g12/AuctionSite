package auctionSite.DAOs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import auctionSite.DAOs.ListingDAOImpl;
import auctionSite.entities.Listing;
import auctionSite.entities.User;


public class ListingDAOImplTest {
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private ListingDAOImpl listDao;
	private Listing listing;
	private TypedQuery query;
	
	@Before
	public void setup(){
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		query = mock(TypedQuery.class);
		listDao = new ListingDAOImpl(factory);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		
	}


	@Test
	public void test_listListing_invokesTransactionMethodsAndPersist() {
		// Arrange
		// Act
		Listing listing = new Listing();
		listDao.listListing(listing);
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).persist(listing);
	}
	
	@Test
	public void test_updateListing_invokesTransactionMethodsAndPersist() {
		// Arrange
		// Act
		Listing listing = new Listing();
		listDao.updateListing(listing);
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).merge(listing);
	}
	
	@Test
	public void test_removeListing_invokesTransactionMethodsAndPersist() {
		// Arrange
		listing = mock(Listing.class);
		when(manager.find(Listing.class, 0)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(0);
		listDao = new ListingDAOImpl(factory);
		// Act
		listDao.removeListing(listing.getListingId());
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).find(Listing.class,0);
		verify(manager).remove(listing);

	}
	
	@Test
	public void test_getListing_invokesFind() {
		// Arrange
		// Act
		listing = mock(Listing.class);
		when(manager.find(Listing.class, 1)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(1);

		listDao.getListing(1);
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).find(Listing.class, 1);
	}
	
	@Test
	public void test_getAllListings_invokesCreateQuery() {
		// Arrange
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		when(manager.createQuery("select listing from Listing listing",Listing.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		// Act

		listDao.getAllListings();
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).createQuery("select listing from Listing listing",Listing.class);
	}
	
	@Test
	public void test_getAllListings_returnsNewList() {
		// Arrange
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		listing = mock(Listing.class);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		when(manager.createQuery("select listing from Listing listing",Listing.class)).thenReturn(query);
		listDao = new ListingDAOImpl(factory);
		// Act

		listDao.getAllListings();
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		assertEquals(query.getResultList(), listingList);
	}
	
	@Test
	public void test_bidOnListing_invokesFindAndMerge() {
		// Arrange
		listing = mock(Listing.class);
		when(manager.find(Listing.class, 0)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(0);
		User user = new User();
		// Act
		listDao.bidOnListing(listing.getListingId(), 17.0, user);
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).find(Listing.class,0);
		verify(manager).merge(listing);

	}
	
	@Test
	public void test_bidOnListing_setsNewPriceWhenBidHigher() {
		// Arrange
		listing = mock(Listing.class);
		when(manager.find(Listing.class, 0)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(0);
		when(listing.getCurrentPrice()).thenReturn(7.0);
		User user = new User();
		// Act
		listDao.bidOnListing(listing.getListingId(), 17.0, user);
		verify(listing).getCurrentPrice();
	}
	
	@Test
	public void test_getAllListingsForAUser_invokesCreateQuery() {
		// Arrange
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		when(manager.createQuery("select listing from Listing listing where listing.user.username = ?", Listing.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		// Act
		listDao.getListOfListingsForUser("liamooo");
		// Assert
		verify(manager).createQuery("select listing from Listing listing where listing.user.username = ?", Listing.class);
	}
	
	@Test
	public void test_getAllListingsForAUser_assertEqualsNewList() {
		// Arrange
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		when(manager.createQuery("select listing from Listing listing where listing.user.username = ?", Listing.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		
		// Act
		listDao.getListOfListingsForUser("liamooo");
		// Assert
		assertEquals(listDao.getListOfListingsForUser("liamooo"),listingList);
	}
	
	
	@Test
	public void test_getListOfListingsForUserWinning_invokesCreateQuery() {
		// Arrange
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		listing = mock(Listing.class);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		when(manager.createQuery("select listing from Listing listing where listing.winningUser.username = ?", Listing.class)).thenReturn(query);
		// Act
		listDao.getListOfListingsForUserWinning("liamooo");
		// Assert
		verify(manager).createQuery("select listing from Listing listing where listing.winningUser.username = ?", Listing.class);
	}
	
	@Test
	public void test_getListOfListingsForUserWinning_assertEqualsNewList() {
		// Arrange
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		listing = mock(Listing.class);
		when(manager.createQuery("select listing from Listing listing where listing.winningUser.username = ?", Listing.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		// Act
		listDao.getListOfListingsForUserWinning("liamooo");
		// Assert
		assertEquals(listDao.getListOfListingsForUserWinning("liamooo"),listingList);
	}


}
