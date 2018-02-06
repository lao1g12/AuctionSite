package com.fdmgroup.AuctionSite.DAOs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.AuctionSite.User;
import com.fdmgroup.springmvcdemo.controllers.Logging;


public class UserMethodDAOImpl implements UserMethodDAO {
	


	@Autowired
	private EntityManagerFactory factory;

	public UserMethodDAOImpl (EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
	public UserMethodDAOImpl() {}

	public User login(String username, String password) throws NoResultException{
		EntityManager manager = factory.createEntityManager();
		User usercheck = null;
		try{
		TypedQuery<User> query = manager.createQuery("select user from User user where user.username = ? and user.password = ?",User.class);
		query.setParameter(1,username);
		query.setParameter(2,password);
		usercheck = query.getSingleResult();
		Logging.Log("info", username+" Succesfully logged in");
		return usercheck;
		}catch (NoResultException no){
			Logging.Log("error", "User not found or incorrect password");
			return null;
		}
		
	}


	public void signUp(User user) throws PersistenceException{
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		user.setRole("User");
		try{
		manager.persist(user);
		Logging.Log("info", "User succesfully registered, adn role set to User");
		} catch (PersistenceException pe){
			Logging.Log("error", "User attempted to register with an already taken username");
		}
		manager.getTransaction().commit();
		

		
	}

	public void removeUser(String username) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		User user = manager.find(User.class, username);
		Logging.Log("info", username+ " Removed");
		manager.remove(user);
		manager.getTransaction().commit();
		manager.close();
		
	}


	public User getUser(String username) {
		EntityManager manager = factory.createEntityManager();
		User user = manager.find(User.class, username);
		Logging.Log("info", user.getUsername()+" was retrieved");
		return user;
		
	}

	@Override
	public void updateUser(User user) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		user.setRole("User");
		manager.merge(user);
		Logging.Log("info", user.getUsername()+"'s information was updated.");
		manager.getTransaction().commit();
		
	}

}
