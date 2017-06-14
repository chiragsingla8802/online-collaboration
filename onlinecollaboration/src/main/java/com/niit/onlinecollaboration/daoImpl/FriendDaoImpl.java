package com.niit.onlinecollaboration.daoImpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.onlinecollaboration.dao.FriendDao;
import com.niit.onlinecollaboration.model.Friend;
import com.niit.onlinecollaboration.model.User_Detail;

@Repository("friendDao")
public class FriendDaoImpl implements FriendDao {

	@Autowired
	SessionFactory sessionFactory;

	public FriendDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	@Transactional
	public boolean addFriend(Friend friends) {
		try {
			sessionFactory.getCurrentSession().save(friends);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<Friend> list(int userId) {
		return sessionFactory.getCurrentSession().createQuery("FROM Friend where userId2 = "+userId).list();
	}

	@Override
	@Transactional
	public boolean updateFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	@Transactional
	public List<User_Detail> myFriends(int id) {
		String selectQuery = "SELECT * FROM USER_DETAIL WHERE userId IN (SELECT userId1 FROM FRIEND WHERE userId2 ='"+ id +"'UNION SELECT userId2 FROM FRIEND WHERE userId1 = '"+ id +"') AND STATUS = 'Approved'";
		//String selectQuery="(SELECT userId1 FROM FRIEND WHERE userId2 ='"+ id +"' AND STATUS = 'Approved') UNION (SELECT userId2 FROM FRIEND WHERE userId1 = '"+ id +"' AND STATUS = 'Approved')";
		return sessionFactory.getCurrentSession().createNativeQuery(selectQuery,User_Detail.class).getResultList();
		//return sessionFactory.getCurrentSession().createQuery(selectQuery).list();
		//return sessionFactory.getCurrentSession().createQuery("FROM Friend WHERE userId2 = '"+ id +"' OR userId1 = '"+ id +"' AND STATUS = 'Approved'", Friend.class).list();
	}

	@Override
	@Transactional
	public List<User_Detail> noFriends(int id) {
		
		String selectQuery = "SELECT * FROM USER_DETAIL WHERE userId NOT IN (SELECT userId1 FROM FRIEND WHERE userId2 = :id OR userId1 = :id UNION SELECT userId2 FROM FRIEND WHERE userId2 = :id OR userId1 = :id) AND STATUS = 'Approved'";
		
		return sessionFactory
				.getCurrentSession()
					.createNativeQuery(selectQuery,User_Detail.class)
						.setParameter("id", id)
							.getResultList();
		
	}



	}

	
