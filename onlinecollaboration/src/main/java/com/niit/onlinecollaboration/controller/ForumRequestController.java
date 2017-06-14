package com.niit.onlinecollaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.onlinecollaboration.dao.ForumDao;
import com.niit.onlinecollaboration.dao.ForumRequestDao;
import com.niit.onlinecollaboration.dao.UserDao;
import com.niit.onlinecollaboration.model.Forum;
import com.niit.onlinecollaboration.model.ForumRequest;
import com.niit.onlinecollaboration.model.User_Detail;

@RestController
@RequestMapping("/forum")
public class ForumRequestController {

	@Autowired
	ForumRequestDao forumRequestDao;
	
	@Autowired
	ForumDao forumDao;
	
	@Autowired
	UserDao userDao;
	
	//Method to send forum join request
			@RequestMapping(value = {"/forum/request/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<ForumRequest> addForumRequest(@PathVariable("id") int id, @RequestBody Integer forumId) {
				System.out.println("Success!");
				ForumRequest forumRequest = new ForumRequest();
				Forum forum = null;
				User_Detail user_Detail = null;
				user_Detail = userDao.getUserDetail(id); //Fetching user by user id
				forumRequest.setUserId(id);
				String username = user_Detail.getUserName();
				forumRequest.setUsername(username);
				forum = forumDao.getForum(forumId); //Fetching forum with forum Id
				forumRequest.setForum(forum);
				if( user_Detail.getRole().equals("Super_Admin") || user_Detail.getRole().equals("Admin") ) {
					forumRequest.setStatus("APPROVED");
				} else {
					forumRequest.setStatus("PENDING");
				}
				
				forumRequestDao.addForumRequest(forumRequest);
				return new ResponseEntity<ForumRequest>(forumRequest, HttpStatus.OK);
			}
			
			
			//Method for fetching list of participated users
			@RequestMapping(value = {"/forum/participatedUsers/list/{id}"}, method = RequestMethod.GET)
			public ResponseEntity<List<ForumRequest>> fetchParticipatedUsers(@PathVariable("id") int id) {
				System.out.println("Fetching list of users");
				List<ForumRequest> forumRequests = forumRequestDao.list(id);
				return new ResponseEntity<List<ForumRequest>>(forumRequests, HttpStatus.OK);
			}

}
