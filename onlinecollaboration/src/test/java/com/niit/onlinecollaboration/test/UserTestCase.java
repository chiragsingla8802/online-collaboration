package com.niit.onlinecollaboration.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.onlinecollaboration.dao.UserDao;
import com.niit.onlinecollaboration.model.User_Detail;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDao userDao;
	private User_Detail user;
	
	@BeforeClass
	public static void init(){
	context = new AnnotationConfigApplicationContext();
	context.scan("com.niit.onlinecollaboration");
	context.refresh();
	System.out.println("---------------------------------reached before bean----------------------------");
	userDao = (UserDao)context.getBean("userDao");
	System.out.println("---------------------------------reached after bean----------------------------");
	}
	
	
	/*@Test
	public void testAddServices(){
		user = new User_Detail();
		
		user.setAddress("delhi");
		user.setUserId(1);
	    user.setState("haryana");
		user.setCity("faridabad");
		user.setEmail("chirag.singla8802@gmail.com");
		user.setName("chirag");
		user.setPassword("chirag1234");
		user.setPhoneNo("9891");
		user.setActive(true);
		user.setRole("Staff");
		user.setIsOnline("Staff");
		user.setUserName("Staff");
		user.setGender('m');
		user.setStatus("approved");
		user.setProfile("chirag");
		user.setBirthDate(LocalDate.parse("1994-05-08"));
		assertEquals("Successfully added a product inside the table!",true,userDao.add(user));
	}*/
	
	/*@Test
	public void testGetServices(){
		user = new User();
		userDto = userDao.getUserDetail(1);
		assertEquals("Successfully fetched a single category from the table!","chirag",userDto.getName());
	}*/
	
	/*@Test
	public void testUpdateServices(){
		user = new User_Detail();
		user = userDao.getUserDetail(1);
		
		user.setCity("ballabgarh");
		
		assertEquals("Successfully update a single category in the table!",true,userDao.update(user));
}*/
	
	/*@Test
	public void testDeletServices(){
		
		
		assertEquals("Successfully delete a single category in the table!",true,userDao.delete(9));
}*/
	
	/*@Test
	public void testListServices(){
		
		assertEquals("Successfully fetched a list of services from the table!",1,userDao.userlist().size());
	}*/
	
}

	

