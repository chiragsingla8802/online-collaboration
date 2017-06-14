package com.niit.onlinecollaboration.dao;

import java.util.List;

import com.niit.onlinecollaboration.model.ForumPosts;

public interface ForumPostDao {

	List<ForumPosts> list();
	List<ForumPosts> list(int id);
	ForumPosts getForumPosts(int id);
	boolean addForumPosts(ForumPosts forumPosts);
	boolean updateForumPosts(ForumPosts forumPosts);
	boolean deleteForumPosts(ForumPosts forumPosts);

}
