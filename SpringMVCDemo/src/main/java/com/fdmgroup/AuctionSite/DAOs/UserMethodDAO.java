package com.fdmgroup.AuctionSite.DAOs;

import com.fdmgroup.AuctionSite.User;

public interface UserMethodDAO {

	public User login(String username, String password);

	public void signUp(User user);

	public void removeUser(String username);
	
	public User getUser(String username);
	
	public void updateUser(User user);

}
