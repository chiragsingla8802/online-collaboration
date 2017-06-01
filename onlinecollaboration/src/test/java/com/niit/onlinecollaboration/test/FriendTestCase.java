package com.niit.onlinecollaboration.test;

import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.onlinecollaboration.dao.FriendDao;
import com.niit.onlinecollaboration.model.Friend;

public class FriendTestCase {

	
	private static AnnotationConfigApplicationContext context;
	private static FriendDao friendDao;
	private Friend friend;
	
	@BeforeClass
	public static void init(){
	context = new AnnotationConfigApplicationContext();
	context.scan("com.niit.onlinecollaboration");
	context.refresh();
	System.out.println("---------------------------------reached before bean----------------------------");
	friendDao = (FriendDao)context.getBean("friendDao");
	System.out.println("---------------------------------reached after bean----------------------------");
	}
	
	/*@Test
	public void testGetServices(){
		user = new User();
		userDto = userDao.getUserDetail(1);
		assertEquals("Successfully fetched a single category from the table!","chirag",userDto.getName());
	}*/
	
	
	
	
}
