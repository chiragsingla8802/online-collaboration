package com.niit.onlinecollaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.onlinecollaboration.dao.UserDao;
import com.niit.onlinecollaboration.model.DomainResponse;
import com.niit.onlinecollaboration.model.User_Detail;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping("/{id}")
	public ResponseEntity<User_Detail> get(@PathVariable int id) {
		System.out.println("-------------------------------------reached into controller--------------------------------------------");
		return new ResponseEntity<User_Detail>(userDao.getUserDetail(id), HttpStatus.OK);
	}

	@PostMapping("/recieve")
	public ResponseEntity<DomainResponse> post(@RequestBody User_Detail user){
		System.out.println("-------------------------------------reached into controller--------------------------------------------");
		userDao.add(user);
		return new ResponseEntity<DomainResponse> (new DomainResponse("user recieved the data",100), HttpStatus.OK);
	}
	
	@RequestMapping("/all")
	public ResponseEntity<List<User_Detail>> get(){
		return new ResponseEntity<List<User_Detail>>(userDao.userlist(),HttpStatus.OK);
	}
}
