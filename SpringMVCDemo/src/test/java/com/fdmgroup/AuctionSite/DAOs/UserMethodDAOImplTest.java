package com.fdmgroup.AuctionSite.DAOs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Test;

import com.fdmgroup.AuctionSite.User;





public class UserMethodDAOImplTest {
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private UserMethodDAOImpl userDao;
	private User user;
	private TypedQuery query;
	private User usercheck;

	@Test
	public void test_signUp_invokesTransactionMethodsAndPersist() {
		// Arrange
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		userDao = new UserMethodDAOImpl(factory);
		// Act
		User user = new User();
		userDao.signUp(user);
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).persist(user);
	}
	
	@Test
	public void test_signUp_invokesTransactionMethods_invokesFindAndRemove() {
		// Arrange
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		user = mock(User.class);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		when(manager.find(User.class, "liam.oliver")).thenReturn(user);
		when(user.getUsername()).thenReturn("liam.oliver");
		userDao = new UserMethodDAOImpl(factory);
		// Act

		userDao.removeUser(user.getUsername());
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).find(User.class,"liam.oliver");
		verify(manager).remove(user);
	}
	
	@Test
	public void test_login_invokesCreateQuery() {
		// Arrange
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		user = mock(User.class);
		query = mock(TypedQuery.class);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.createQuery("select user from User user where user.username = ? and user.password = ?", User.class)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(usercheck);
		userDao = new UserMethodDAOImpl(factory);
		// Act
		userDao.login("cheetos", "happy");
		// Assert
		verify(manager).createQuery("select user from User user where user.username = ? and user.password = ?", User.class);
	}
	
	@Test
	public void test_getUser_invokesFind() {
		// Arrange
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		user = mock(User.class);
		String username = "liam.oliver";
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		when(manager.find(User.class, username)).thenReturn(user);
		userDao = new UserMethodDAOImpl(factory);
		// Act

		userDao.getUser(username);
		
		// Assert

		verify(manager).find(User.class, username);
	}
	
	@Test
	public void test_updateUser_invokesTransactionMethodsAndPersist() {
		// Arrange
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		when(factory.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		userDao = new UserMethodDAOImpl(factory);
		// Act
		User user = new User();
		userDao.updateUser(user);
		// Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).merge(user);
	}

	

}
