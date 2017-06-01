package com.niit.onlinecollaboration.model;

public class FriendListModel {

Friend friend;
	
	User_Detail user_Detail;
	
	User_Detail friendDetails;

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	public User_Detail getUserDetails() {
		return user_Detail;
	}

	public void setUserDetails(User_Detail user_Detail) {
		this.user_Detail = user_Detail;
	}

	public User_Detail getFriendDetails() {
		return friendDetails;
	}

	public void setFriendDetails(User_Detail friendDetails) {
		this.friendDetails = friendDetails;
	}

}
