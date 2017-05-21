package com.niit.onlinecollaboration.dao;

import java.util.List;

import com.niit.onlinecollaboration.model.Friend;
import com.niit.onlinecollaboration.model.User_Detail;

public interface FriendDao {

	public List<Friend> getAllFriends();
	
	public List<Friend> getMyFriendList(int userId);
	
	public List<User_Detail> searchAllUsers(int userId);
	
	public List<User_Detail> searchSentRequests(int userId);
	
	public boolean update(Friend friend);
	
	public boolean isFriendRequestSent(int userId,int friendId);
	
	public boolean isFriend(int userId,int friendId);
	
	public boolean sendFriendRequest(Friend friend);	
	
	public Friend getByRequest(int userId,int friendId);
	
	public boolean rejectRequest(Friend friend);
	
	public boolean acceptRequest(Friend friend);
	
	public boolean unFriend(int userId,int friendId);
}
