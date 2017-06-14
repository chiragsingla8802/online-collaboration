package com.niit.onlinecollaboration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.onlinecollaboration.dao.FriendDao;
import com.niit.onlinecollaboration.dao.UserDao;
import com.niit.onlinecollaboration.model.Friend;
import com.niit.onlinecollaboration.model.User_Detail;
@RequestMapping("/friend")
@RestController
public class FriendController {

	@Autowired
	Friend friend;

	@Autowired
	FriendDao friendDao;

	
	@Autowired
	UserDao userDao;
	//tested with postman
	//Method to send friend request
			@RequestMapping(value = {"/friendRequest/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<Friend> sendFriendRequest(@PathVariable("id") int id, @RequestBody int initId) {
					System.out.println("Sending friend request now!");
					Friend friend = new Friend();
					User_Detail user = userDao.getUserDetail(id); //Fetching friend by friend id
					friend.setFriendId(id);
					friend.setUserId(initId);
					friend.setStatus("PENDING");
					friendDao.addFriend(friend);
					return new ResponseEntity<Friend>(friend, HttpStatus.OK);
				}

			
			//Method to fetch friend requests
			@RequestMapping(value = {"/friendRequest/list/{id}"}, method = RequestMethod.GET)
			public ResponseEntity<List<User_Detail>> fetchRequest(@PathVariable("id") int userId) {
					System.out.println("Fetchng list of friend request received");
					List<Friend> friend = friendDao.list(userId);
					List<User_Detail> users = new ArrayList<>();
					for(Friend fr : friend) {
						if(fr.getStatus().equals("PENDING")) {
							User_Detail user = userDao.getUserDetail(fr.getUserId());
							users.add(user);
						}
					}
					return new ResponseEntity<List<User_Detail>>(users, HttpStatus.OK);
				}
		
			
			//Method to approve friend requests
			@RequestMapping(value = {"/friendRequest/approve/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<Friend> approveRequest(@PathVariable("id") int id, @RequestBody Integer userId) {
					System.out.println("Fetchng list of friend request received");
					List<Friend> friend = friendDao.list(userId);
					List<User_Detail> users = new ArrayList<>();
					for(Friend fr : friend) {
						if(fr.getUserId() == id) {
							fr.setStatus("APPROVED");
							friendDao.updateFriend(fr);
						}
					}
					return new ResponseEntity<Friend>(HttpStatus.OK);
				}
			
			//Method to reject friend requests
			@RequestMapping(value = {"/friendRequest/reject/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<Friend> rejectRequest(@PathVariable("id") int id, @RequestBody Integer userId) {
					System.out.println("Fetchng list of friend request received");
					List<Friend> friend = friendDao.list(userId);
					List<User_Detail> users = new ArrayList<>();
					for(Friend fr : friend) {
						if(fr.getUserId() == id) {
							fr.setStatus("rejected");
							friendDao.updateFriend(fr);
						}
					}
					return new ResponseEntity<Friend>(HttpStatus.OK);
				}
			
			@RequestMapping(value = {"/user/friends/model/{id}"}, method = RequestMethod.GET)
			public ResponseEntity<List<User_Detail>> users(@PathVariable("id") int userId) {
					System.out.println("Fetchng friends from user");
					List<User_Detail> users = friendDao.noFriends(userId);  
					return new ResponseEntity<List<User_Detail>>(users, HttpStatus.OK);
				}
			
			//function to fetch user's friends
			@RequestMapping(value = {"/my/friends/{userId}"}, method = RequestMethod.GET)
			public ResponseEntity<List<User_Detail>> fetchMyFriends(@PathVariable("userId") int id) {
					System.out.println("Fetchng friends");
					List<User_Detail> users = friendDao.myFriends(id);
					//List<User_Detail> myFriends = new ArrayList<>();
//					for(User_Detail user1 : users) {
//						if(user1.getUserId() != id) {
//							myFriends.add(user1);
//						}
//					}
					System.out.println("Successfully fetch friends");
					return new ResponseEntity<List<User_Detail>>(users, HttpStatus.OK);
				}
}
