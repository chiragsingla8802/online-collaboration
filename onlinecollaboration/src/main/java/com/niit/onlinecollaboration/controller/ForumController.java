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
import com.niit.onlinecollaboration.dao.ForumPostDao;
import com.niit.onlinecollaboration.dao.ForumRequestDao;
import com.niit.onlinecollaboration.dao.UserDao;
import com.niit.onlinecollaboration.model.Forum;
import com.niit.onlinecollaboration.model.ForumModel;
import com.niit.onlinecollaboration.model.ForumPosts;
import com.niit.onlinecollaboration.model.ForumRequest;
import com.niit.onlinecollaboration.model.User_Detail;

@RestController
@RequestMapping("/forum")
public class ForumController {

	@Autowired
	ForumDao forumDao;
	
	@Autowired
	UserDao userDao;
	
	
	
	@Autowired
	ForumPostDao forumPostDao;
	
	@Autowired
	ForumRequestDao forumRequestDao;
	
	//Method for creating new forum category
		@RequestMapping(value = {"/forum/new"}, method = RequestMethod.POST)
		public ResponseEntity<Forum> addForumCategory(@RequestBody Forum forum) {
			/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now(); 
			forum.setPostDate(LocalDate.parse(dtf.format(now)));*/
			forum.setStatus("APPROVED");
			forum.setNoOfPosts(0);
			User_Detail user_Detail = null;	//creating instance of user
			int id = forum.getUserId();	//retrieving user id from forum
			user_Detail = userDao.getUserDetail(id);	//fetching user detail by its id
			forumDao.addForum(forum);
			int forumId = forum.getId();
			ForumRequest fr = new ForumRequest();
			fr.setUserId(id);
			fr.setUsername(user_Detail.getUserName());
			fr.setStatus("APPROVED");
			fr.setForum(forum);
			forumRequestDao.addForumRequest(fr);
			
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}


		//Method for fetching list of all forum categories
		@RequestMapping(value = {"/forum/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Forum>> fetchForums() {
			System.out.println("Method called");
			List<Forum> forums = forumDao.list();
			return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
		}

		//Method for viewing single forum using forum id as a parameter
		
		@RequestMapping(value = {"/forum/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<ForumModel> viewForum(@PathVariable("id") int id) {
			System.out.println("Calling method");
			ForumModel forumModel = new ForumModel();
			Forum forum = forumDao.getForum(id);
			User_Detail user_Detail = userDao.getUserDetail(forum.getUserId());
			forumModel.setForum(forum);
			forumModel.setUser_Detail(user_Detail);
			return new ResponseEntity<ForumModel>(forumModel, HttpStatus.OK);
				
			}
		
		@RequestMapping(value = {"/forum/post/new/{id}"}, method = RequestMethod.POST)
		public ResponseEntity<ForumPosts> addForumPost(@PathVariable("id") int id, @RequestBody ForumPosts forumPosts) {
			Forum forum=new Forum();
			System.out.println("Adding forum post now");
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//LocalDateTime now = LocalDateTime.now(); 
			//forumPosts.setPostDate(LocalDate.parse(dtf.format(now)));
			forum = forumDao.getForum(id);
			forum.setNoOfPosts(forum.getNoOfPosts() + 1);
			forumDao.updateForum(forum);
			forumPosts.setForum(forum);
			forumPosts.setUserProfileId(userDao.getUserDetail(forumPosts.getUserId()).getProfile());
			forumPostDao.addForumPosts(forumPosts);
			
			return new ResponseEntity<ForumPosts>(forumPosts, HttpStatus.OK);	
		}

		//Function to fetch forum post list
		 @RequestMapping(value = {"/forum/posts/list/{id}"}, method = RequestMethod.GET)
		 public ResponseEntity<List<ForumPosts>> fetchForumPosts(@PathVariable("id") int id) {
				System.out.println("fetching list of forum posts now");
				List<ForumPosts> forumPosts = forumPostDao.list(id);
				return new ResponseEntity<List<ForumPosts>>(forumPosts, HttpStatus.OK);
		}


}