package com.niit.onlinecollaboration.dao;

import java.util.List;

import com.niit.onlinecollaboration.model.ForumRequest;

public interface ForumRequestDao {

	List<ForumRequest> list();
	List<ForumRequest> list(String status);
	List<ForumRequest> list(int id);
	ForumRequest getForumRequest(int id);
	boolean addForumRequest(ForumRequest forumRequest);
	boolean updateForumRequest(ForumRequest forumRequest);
	boolean deleteForumRequest(ForumRequest forumRequest);

}
