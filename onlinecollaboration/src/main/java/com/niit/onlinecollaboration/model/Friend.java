package com.niit.onlinecollaboration.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Friend  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotNull
	private int userId;
	@NotNull
	private int friendId;
	@NotNull
	private String status;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId",nullable=false,insertable=false,updatable=false)
	private User_Detail sender; 
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="friendId",nullable=false,insertable=false,updatable=false)
	private User_Detail receiver;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User_Detail getSender() {
		return sender;
	}

	public void setSender(User_Detail sender) {
		this.sender = sender;
	}

	public User_Detail getReceiver() {
		return receiver;
	}

	public void setReceiver(User_Detail receiver) {
		this.receiver = receiver;
	}
	

	
}
