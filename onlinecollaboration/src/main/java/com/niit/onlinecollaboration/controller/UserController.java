package com.niit.onlinecollaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.onlinecollaboration.dao.UserDao;
import com.niit.onlinecollaboration.model.DomainResponse;
import com.niit.onlinecollaboration.model.User_Detail;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	HttpSession session;


	
	//get the user by his id
	//tested with postman
	@RequestMapping("/get/{id}")
	public ResponseEntity<User_Detail> getUserById(@PathVariable int id) {
		System.out.println("-------------------------------------reached into controller--------------------------------------------");
		return new ResponseEntity<User_Detail>(userDao.getUserDetail(id), HttpStatus.OK);
	}

	//add the user by registeration form
	//tested with postman
	@PostMapping("/insert")
	public ResponseEntity<DomainResponse> post(@RequestBody User_Detail user){
		System.out.println("-------------------------------------reached into controller1--------------------------------------------");
		userDao.add(user);
		System.out.println("-------------------------------user adeed successfully--------------");
		return new ResponseEntity<DomainResponse> (new DomainResponse("user recieved the data",100), HttpStatus.OK);
	}
	
	//get list of all users
	//tested with postman
	@RequestMapping("/all")
	public ResponseEntity<List<User_Detail>> getList(){
		return new ResponseEntity<List<User_Detail>>(userDao.userlist(),HttpStatus.OK);
	}
	
	//delete user by his id
	//tested with postman
	@RequestMapping("/delete/{id}")
	public ResponseEntity<DomainResponse> deleteUserById(@PathVariable int id) {
		userDao.delete(id); 
		return new ResponseEntity<DomainResponse>(new DomainResponse("deleted the data",100), HttpStatus.OK);

	}
	
	//update user by his id
	//tested with postman
	@PostMapping("/update/{id}")
	public ResponseEntity<DomainResponse> updateUserWithId(@RequestBody User_Detail user,@PathVariable int id){
		
		User_Detail currentUser = userDao.getUserDetail(id);
		currentUser.setUserId(user.getUserId());
		currentUser.setAddress(user.getAddress());
		currentUser.setActive(user.isActive());
		currentUser.setCity(user.getCity());
		currentUser.setEmail(user.getEmail());
		currentUser.setGender(user.getGender());
		currentUser.setIsOnline(user.getIsOnline());
		currentUser.setName(user.getName());
		currentUser.setPassword(user.getPassword());
		currentUser.setPhoneNo(user.getPhoneNo());
		currentUser.setProfile(user.getProfile());
		currentUser.setRole(user.getRole());
		currentUser.setState(user.getState());
		currentUser.setUserName(user.getUserName());
		currentUser.setStatus(user.getStatus());
		currentUser.setBirthDate(user.getBirthDate());
		userDao.update(currentUser);
		
		return new ResponseEntity<DomainResponse> (new DomainResponse("user recieved the data",100), HttpStatus.OK);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<DomainResponse> updateUser(@RequestBody User_Detail user) {
		System.out.println("reached into update user");
		User_Detail currentUser = userDao.getUserDetail(user.getUserId());
		currentUser.setAddress(user.getAddress());		
		currentUser.setCity(user.getCity());
		currentUser.setEmail(user.getEmail());		
		currentUser.setName(user.getName());
		currentUser.setPassword(user.getPassword());
		currentUser.setPhoneNo(user.getPhoneNo());		
		currentUser.setRole(user.getRole());
		currentUser.setState(user.getState());
		userDao.update(currentUser);
		System.out.println("user updated successfully");
		return new ResponseEntity<DomainResponse> (new DomainResponse("user recieved the data",100), HttpStatus.OK);
	}
	
	//Admin should able to make one of the employee as admin
	//tested with postman
		@PutMapping(value = "/makeAdmin/{id}")
		public ResponseEntity<DomainResponse> makeAdmin(@PathVariable("id") int empID) {
		User_Detail	user = userDao.getUserDetail(empID);

			if (user == null) {
				user = new User_Detail();
				return new ResponseEntity<DomainResponse> (new DomainResponse("user not exist",100), HttpStatus.OK); // 200

			}
			
			/*if(user.getRole()!="Employee")
			{
				System.out.println("-----------------------------------"+user.getRole()+"-------------------------");
				user = new User_Detail();
				return new ResponseEntity<DomainResponse> (new DomainResponse("we can not make this user as admin",100), HttpStatus.OK); // 200
				
			}*/
			user.setRole("Admin");
			userDao.update(user);
			return new ResponseEntity<DomainResponse> (new DomainResponse("Successfully assign Admin role to the employee",100), HttpStatus.OK);
		}
		
		//method to get all the users who are not my friend
		@RequestMapping(value = "/listAllUsersNotFriends", method = RequestMethod.GET)
		public ResponseEntity<List<User_Detail>> listAllUsersNotFriends() {
		int loggedInUserID = (int) session.getAttribute("loggedInUserID");
			List<User_Detail> users = userDao.notMyFriendList(loggedInUserID);
			return new ResponseEntity<List<User_Detail>>(users, HttpStatus.OK);
		}

		//method to accept a user
		//tested with postman
		@RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
		public ResponseEntity<User_Detail> accept(@PathVariable("id") int id) {
			User_Detail	user = new User_Detail();
			user = updateStatus(id,"Approved");
			return new ResponseEntity<User_Detail>(user, HttpStatus.OK);
		}
		
		//method to reject a user
		//tested with postman
		@RequestMapping(value = "/reject/{id}", method = RequestMethod.GET)
		public ResponseEntity<User_Detail> reject(@PathVariable("id") int id) {
			User_Detail	user = new User_Detail();
			user = updateStatus(id,"Rejected");
			return new ResponseEntity<User_Detail>(user, HttpStatus.OK);

		}


		private User_Detail updateStatus(int id, String status) {
			User_Detail	user = new User_Detail();
			user = userDao.getUserDetail(id);
			user.setStatus(status);
			userDao.update(user);
			return user;

		}
		
		//method to get profile details of current login user
		//tested with postman
		@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
		public ResponseEntity<User_Detail> myProfile() {
			int CurrentUserID = (int) session.getAttribute("CurrentUserID");
			User_Detail user = userDao.getUserDetail(CurrentUserID);
			return new ResponseEntity<User_Detail>(user, HttpStatus.OK);
		}
		
		//method to login user
		//tested with postman
		@PostMapping("/login")
		public ResponseEntity<User_Detail> login(@RequestBody User_Detail user) {
			System.out.println("reached into login");
			user = userDao.authenticate(user.getUserId(), user.getPassword());
			System.out.println("id and password given to user");
				userDao.setOnline(user.getUserId());;
				session.setAttribute("CurrentUserID", user.getUserId());
				int CurrentUserID = (int) session.getAttribute("CurrentUserID");
				System.out.println("logged in user id is:"+CurrentUserID);
				session.setAttribute("CurrentUserRole", user.getRole());
				String CurrentUserRole = (String) session.getAttribute("CurrentUserRole");
				System.out.println("logged in user Role is:"+CurrentUserRole);
				//friendDAO.setOnline(user.getId());
				userDao.setOnline(user.getUserId());
			return new ResponseEntity<User_Detail>(user, HttpStatus.OK);
		}

		//method to logout user
		//tested with postman
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public ResponseEntity<DomainResponse> logout(HttpSession session) {
			int CurrentUserID = (int) session.getAttribute("CurrentUserID");
			System.out.println("logged in user id is:"+CurrentUserID);
			userDao.setOffLine(CurrentUserID);
			session.invalidate();
			System.out.println("id and password released off");
			return new ResponseEntity<DomainResponse> (new DomainResponse("Successfully logged out",100), HttpStatus.OK);
		};

		


}
