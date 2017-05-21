package com.niit.onlinecollaboration.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="BLOG_DETAIL")
public class Blog implements Serializable{

	private static final long serialVersionUID = 8902970462892867386L;

	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="USER_SEQ", allocationSize = 1)
	private int blogId;
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogStatus() {
		return blogStatus;
	}
	public void setBlogStatus(String blogStatus) {
		this.blogStatus = blogStatus;
	}
	public String getBlogDescription() {
		return blogDescription;
	}
	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}
	public LocalDate getPostDate() {
		return postDate;
	}
	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}
	public int getNoOfLikes() {
		return noOfLikes;
	}
	public void setNoOfLikes(int noOfLikes) {
		this.noOfLikes = noOfLikes;
	}
	public int getNoOfComments() {
		return noOfComments;
	}
	public void setNoOfComments(int noOfComments) {
		this.noOfComments = noOfComments;
	}
	public int getNoOfViews() {
		return noOfViews;
	}
	public void setNoOfViews(int noOfViews) {
		this.noOfViews = noOfViews;
	}
	
	public User_Detail getUser_Detail() {
		return user_Detail;
	}
	public void setUser_Detail(User_Detail user_Detail) {
		this.user_Detail = user_Detail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String blogName;
	private String blogStatus;
	private String blogDescription;
	@Column(name="Post_Date")
	private LocalDate postDate;
	@Column(name="No_Of_Likes")
	private int noOfLikes;
	@Column(name="No_Of_Comments")
	private int noOfComments;
	@Column(name="No_Of_Views")
	private int noOfViews;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User_Detail user_Detail;
	@Column(name="User_Name")
	private String userName;
	
	/*@OneToMany(mappedBy="blog", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BlogComments> blogComments;*/



	
	
}

