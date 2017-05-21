package com.niit.onlinecollaboration.dao;

import java.util.List;

import com.niit.onlinecollaboration.model.User_Detail;

public interface UserDao {

	
	boolean add(User_Detail user);
	boolean delete(int userId);
	boolean update(User_Detail user);
	User_Detail getUserDetail(int userId);
	List<User_Detail> userlist();	
	public List<User_Detail> notMyFriendList(int userId);
	public User_Detail get(int userId, String password);
	public User_Detail authenticate(int userId, String password);
	public void setOnline(int userId);
	public void setOffLine(int userId);

}
