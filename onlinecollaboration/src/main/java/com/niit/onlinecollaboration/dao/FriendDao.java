package com.niit.onlinecollaboration.dao;

import java.util.List;

import com.niit.onlinecollaboration.model.Friend;
import com.niit.onlinecollaboration.model.User_Detail;

public interface FriendDao {

	//boolean saveOrUpdate(Friend friend);

	//boolean delete(Friend friend);

	//Friend get(int userId, int friendId);

	//List<Friend> list();
	
	boolean updateFriend(Friend friend);
	
	List<User_Detail> myFriends(int id);
	
	List<Friend> list(int userId);
	
	//List<Friend> getFriends(int userId);
	
	
	//List<Friend> getRequest(int userId);
	
	//List<Friend> getTopFriends(int n);
	
	boolean addFriend(Friend friends);

}
