package com.niit.onlinecollaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.niit.onlinecollaboration.dao.FriendDao;
import com.niit.onlinecollaboration.dao.UserDao;
import com.niit.onlinecollaboration.model.Friend;
import com.niit.onlinecollaboration.model.User_Detail;

public class FriendController {

	@Autowired
	UserDao userDao;
	
	@Autowired 
	User_Detail user_Detail;
	@Autowired
	private FriendDao friendDao;

	@Autowired
	private Friend friend;
	@Autowired
	HttpSession session; 
	

	@GetMapping("/searchAll")
	public ResponseEntity<List<User_Detail>> searchUsers() {
		List<User_Detail> users = friendDao.searchAllUsers((int) session.getAttribute("loggedInUserID"));
		return new ResponseEntity<List<User_Detail>>(users, HttpStatus.OK);

	}
	
	@GetMapping("/searchSentRequests")
	public ResponseEntity<List<User_Detail>> searchSentRequests() {
		List<User_Detail> users = friendDao.searchSentRequests((int) session.getAttribute("loggedInUserID"));
		return new ResponseEntity<List<User_Detail>>(users, HttpStatus.OK);

	}
	
	@PutMapping("/sendRequest/{userid}")
	public ResponseEntity<Friend> sendRequest(@PathVariable("userid")int userid){
		if(friendDao.isFriendRequestSent((int) session.getAttribute("loggedInUserID"), userid)==false){
			friend.setUserId((int) session.getAttribute("loggedInUserID"));
			friend.setFriendId(userid);
			friend.setStatus("N");
			friendDao.sendFriendRequest(friend);
		}
		else{
			friend = friendDao.getByRequest((int) session.getAttribute("loggedInUserID"),userid);
			if(friend.getStatus().equals("R")){
				friend.setStatus("N");
				friendDao.update(friend);
			}
		}
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@GetMapping("/getFriendlist")
	public ResponseEntity<List<Friend>> friendList(){
		List<Friend> friendlist = friendDao.getMyFriendList((int) session.getAttribute("loggedInUserID"));
		return new ResponseEntity<List<Friend>>(friendlist,HttpStatus.OK);
	}
	
	@PutMapping("/acceptRequest/{userid}/")
	public ResponseEntity<Friend> acceptRequest(@PathVariable("userid")int userId){
		friend = friendDao.getByRequest(userId,(int) session.getAttribute("loggedInUserID"));
		if(friend!=null){
			friend.setStatus("A");
			friendDao.acceptRequest(friend);
		}
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@PutMapping("/rejectRequest/{userid}/")
	public ResponseEntity<Friend> rejectRequest(@PathVariable("userid")int userId){
		friend = friendDao.getByRequest(userId,(int) session.getAttribute("loggedInUserID"));
		if(friend!=null){			
			friend.setStatus("R");
			friendDao.rejectRequest(friend);
		}		
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@PutMapping("/unFriend/{userid}/")
	public ResponseEntity<Friend> unFriend(@PathVariable("userid")int friendId){
		friendDao.unFriend((int) session.getAttribute("loggedInUserID"),friendId);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}

	
	
	
}
