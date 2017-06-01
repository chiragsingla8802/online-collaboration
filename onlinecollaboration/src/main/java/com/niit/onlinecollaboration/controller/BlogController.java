package com.niit.onlinecollaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.onlinecollaboration.dao.BlogDao;
import com.niit.onlinecollaboration.model.Blog;
import com.niit.onlinecollaboration.model.DomainResponse;

@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogDao blogDao;
	
	/*mapping to get the list of blogs*/
	@RequestMapping("/all")
	public ResponseEntity<List<Blog>> get(){
		System.out.println("fetch all blogs is called");
		return new ResponseEntity<List<Blog>>(blogDao.list(),HttpStatus.OK);
	}
	
	@RequestMapping("/all/{status}")
	public ResponseEntity<List<Blog>> getByStatus(@PathVariable String status){
		return new ResponseEntity<List<Blog>>(blogDao.getBlogsByStatus(status),HttpStatus.OK);
	}
	
	/*mapping to get the particular blog with id*/
	@RequestMapping("/get/{id}")
	public ResponseEntity<Blog> get(@PathVariable int id) {
		System.out.println("-------------------------------------reached into controller--------------------------------------------");
		return new ResponseEntity<Blog>(blogDao.getBlog(id), HttpStatus.OK);
	}

	/*mapping to add blog to blog table*/
	@PostMapping("/insert")
	public ResponseEntity<DomainResponse> post(@RequestBody Blog blog, HttpSession session){
		System.out.println("-------------------------------------reached into controller1--------------------------------------------");
		//blog.setUserId(CurrentUserID);
		blogDao.addBlog(blog);
		System.out.println("-------------------------------blog adeed successfully--------------");
		return new ResponseEntity<DomainResponse> (new DomainResponse("blog table recieved the data",100), HttpStatus.OK);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<DomainResponse> updateBlog(@RequestBody Blog blog,@PathVariable int id){
		
		Blog currentBlog = blogDao.getBlog(id);
		currentBlog.setBlogId(blog.getBlogId());
		currentBlog.setBlogName(blog.getBlogName());
		currentBlog.setBlogDescription(blog.getBlogDescription());
		currentBlog.setBlogStatus(blog.getBlogStatus());
		currentBlog.setNoOfComments(blog.getNoOfComments());
		currentBlog.setNoOfLikes(blog.getNoOfLikes());
		currentBlog.setNoOfViews(blog.getNoOfViews());
		currentBlog.setPostDate(blog.getPostDate());
//		currentBlog.setUserId(blog.getUserId());
		currentBlog.setUserName(blog.getUserName());
		
		blogDao.updateBlog(currentBlog);
		
		return new ResponseEntity<DomainResponse> (new DomainResponse("blog is updated",100), HttpStatus.OK);
	}
	
	@RequestMapping("/delete/{id}")
	public ResponseEntity<DomainResponse> deleteBlogById(@PathVariable int id) {
		Blog blog = new Blog();
		blog=blogDao.getBlog(id);
		blogDao.deleteBlog(blog); 
		return new ResponseEntity<DomainResponse>(new DomainResponse("deleted the data",100), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/accept_blog/{id}")
	public ResponseEntity<DomainResponse> accept(@PathVariable int id) {
		Blog blog = updateStatus(id, "Approved");
		return new ResponseEntity<DomainResponse>(new DomainResponse("accepted the blog",100), HttpStatus.OK);

	}

	@RequestMapping(value = "/reject_blog/{id}}")
	public ResponseEntity<DomainResponse> reject(@PathVariable int id) {
		Blog blog = updateStatus(id, "Rejected");
		return new ResponseEntity<DomainResponse>(new DomainResponse("rejected the blog",100), HttpStatus.OK);

	}
	
	private Blog updateStatus(int blogId, String blogStatus) {
		Blog blog = blogDao.getBlog(blogId);
			blog.setBlogStatus(blogStatus);
			blogDao.updateBlog(blog);
		return blog;

	}

}
