package auctionSite.DAOs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import auctionSite.DAOs.ListingDAOImpl;
import auctionSite.entities.Listing;
import auctionSite.entities.OldListing;
import auctionSite.entities.User;

public class ListingDAOImplTest {
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private ListingDAOImpl listDao;
	private Listing listing;
	private TypedQuery query;
	private OldListing oldListing;

	@Before
	public void setup() {
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
		Listing listing = new Listing();
		listDao.listListing(listing);
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).persist(listing);
	}

	@Test
	public void test_updateListing_invokesTransactionMethodsAndPersist() {
		Listing listing = new Listing();
		listDao.updateListing(listing);
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).merge(listing);
	}

	@Test
	public void test_removeListing_invokesTransactionMethodsAndPersist() {
		listing = mock(Listing.class);
		when(manager.find(Listing.class, 0)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(0);
		listDao = new ListingDAOImpl(factory);
		listDao.removeListing(listing.getListingId());
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).find(Listing.class, 0);
		verify(manager).remove(listing);

	}

	@Test
	public void test_getListing_invokesFind() {
		listing = mock(Listing.class);
		when(manager.find(Listing.class, 1)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(1);
		listDao.getListing(1);
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).find(Listing.class, 1);
	}

	@Test
	public void test_getAllListings_invokesCreateQuery() {
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		when(manager.createQuery("select listing from Listing listing", Listing.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		listDao.getAllListings();
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).createQuery("select listing from Listing listing", Listing.class);
	}

	@Test
	public void test_getAllListings_returnsNewList() {
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		listing = mock(Listing.class);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		when(manager.createQuery("select listing from Listing listing", Listing.class)).thenReturn(query);
		listDao = new ListingDAOImpl(factory);
		listDao.getAllListings();
		verify(transaction).begin();
		verify(transaction).commit();
		assertEquals(query.getResultList(), listingList);
	}

	@Test
	public void test_bidOnListing_invokesFindAndMerge() {
		listing = mock(Listing.class);
		when(manager.find(Listing.class, 0)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(0);
		User user = new User();
		listDao.bidOnListing(listing.getListingId(), 17.0, user);
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).find(Listing.class, 0);
		verify(manager).merge(listing);

	}

	@Test
	public void test_bidOnListing_setsNewPriceWhenBidHigher() {
		listing = mock(Listing.class);
		when(manager.find(Listing.class, 0)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(0);
		when(listing.getCurrentPrice()).thenReturn(7.0);
		User user = new User();
		listDao.bidOnListing(listing.getListingId(), 17.0, user);
		verify(listing).getCurrentPrice();
	}

	@Test
	public void test_bidOnListing_bidTooLow() {
		listing = mock(Listing.class);
		when(manager.find(Listing.class, 0)).thenReturn(listing);
		when(listing.getListingId()).thenReturn(0);
		when(listing.getCurrentPrice()).thenReturn(24.0);
		User user = new User();
		listDao.bidOnListing(listing.getListingId(), 17.0, user);
		verify(listing, (times(2))).getCurrentPrice();
	}

	@Test
	public void test_getAllListingsForAUser_invokesCreateQuery() {
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		when(manager.createQuery("select listing from Listing listing where listing.user.username = ?", Listing.class))
				.thenReturn(query);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		listDao.getListOfListingsForUser("liamooo");
		verify(manager).createQuery("select listing from Listing listing where listing.user.username = ?",
				Listing.class);
	}

	@Test
	public void test_getAllListingsForAUser_assertEqualsNewList() {
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		when(manager.createQuery("select listing from Listing listing where listing.user.username = ?", Listing.class))
				.thenReturn(query);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);

		listDao.getListOfListingsForUser("liamooo");
		assertEquals(listDao.getListOfListingsForUser("liamooo"), listingList);
	}

	@Test
	public void test_getListOfListingsForUserWinning_invokesCreateQuery() {
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		listing = mock(Listing.class);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		when(manager.createQuery("select listing from Listing listing where listing.winningUser.username = ?",
				Listing.class)).thenReturn(query);
		listDao.getListOfListingsForUserWinning("liamooo");
		verify(manager).createQuery("select listing from Listing listing where listing.winningUser.username = ?",
				Listing.class);
	}

	@Test
	public void test_getListOfListingsForUserWinning_assertEqualsNewList() {
		listing = mock(Listing.class);
		List<Listing> listingList = mock(List.class);
		listing = mock(Listing.class);
		when(manager.createQuery("select listing from Listing listing where listing.winningUser.username = ?",
				Listing.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(listingList);
		when(listingList.size()).thenReturn(1);
		listDao.getListOfListingsForUserWinning("liamooo");
		assertEquals(listDao.getListOfListingsForUserWinning("liamooo"), listingList);
	}

	// OldListings

	@Test
	public void test_addOldListing_invokesTransactionMethodsAndPersist() {
		OldListing oldListing = new OldListing();
		listDao.AddOldListing(oldListing);
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).persist(oldListing);
	}

	@Test
	public void test_getOldListingsForUser_assertEqualsOldListingList() {
		oldListing = mock(OldListing.class);
		List<OldListing> oldListingList = mock(List.class);
		when(manager.createQuery("select listing from OldListing listing where listing.winningUser.username = ?",
				OldListing.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(oldListingList);
		when(oldListingList.size()).thenReturn(1);
		assertEquals(listDao.getOldListingsForUser("liamooo"), oldListingList);
	}

	@Test
	public void test_updateOldListing_invokesTransactionMethodsAndPersist() {
		OldListing oldListing = new OldListing();
		listDao.updateOldListing(oldListing);
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).merge(oldListing);
	}

	@Test
	public void test_getOldListing_invokesFind() {
		oldListing = mock(OldListing.class);
		when(manager.find(OldListing.class, 1)).thenReturn(oldListing);
		when(oldListing.getListingId()).thenReturn(1);
		listDao.getOldListing(1);
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).find(OldListing.class, 1);
	}

	@Test
	public void test_getAllWonListingsForAUser_assertEqualsNewList() {
		oldListing = mock(OldListing.class);
		List<OldListing> oldListingList = mock(List.class);
		when(manager.createQuery("select oldListing from OldListing oldListing where oldListing.user.username = ?",
				OldListing.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(oldListingList);
		when(oldListingList.size()).thenReturn(1);
		assertEquals(listDao.getOldListingsForSold("liamooo"), oldListingList);
	}
}
