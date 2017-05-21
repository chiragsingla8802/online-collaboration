package com.niit.onlinecollaboration.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.onlinecollaboration.dao.FriendDao;
import com.niit.onlinecollaboration.model.Friend;
import com.niit.onlinecollaboration.model.User_Detail;
@Repository("friendDao")
@Transactional
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Friend> getAllFriends() {
		return sessionFactory.getCurrentSession().createQuery("from Friend").list();
	}

	@Override
	public List<Friend> getMyFriendList(int userId) {
		return sessionFactory.getCurrentSession().createQuery("from Friend where friendId='" + userId + "'").list();
	}

	@Override
	public List<User_Detail> searchAllUsers(int userId) {
	//	String hql = "from User u where u.userid not in (select f.friendid from Friend f where (f.useridd='"+userid+"' or f.friendid='"+userid+"') and (f.status='A' or f.status='N')) and u.userid not in (select f.useridd from Friend f where (f.useridd='"+userid+"' or f.friendid='"+userid+"') and (f.status='A' or f.status='N')) and u.role not in ('admin')";
	//	return sessionFactory.getCurrentSession().createQuery(hql).list();
		return null;
	}

	@Override
	public List<User_Detail> searchSentRequests(int userId) {
	//	String hql = "from User u where u.userid in (select f.friendid from Friend f where (f.useridd='"+userid+"') and (f.status='N')) and u.role not in ('admin')";
	//	return sessionFactory.getCurrentSession().createQuery(hql).list();
		return null;
	}

	@Override
	public boolean update(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isFriendRequestSent(int userId, int friendId) {
		String hql = "from Friend where userId='" + userId + "' and friendId='" + friendId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (query.uniqueResult() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFriend(int userId, int friendId) {
		String hql = "from Friend where userId='" + userId + "' and friendId='" + friendId
				+ "' and status='A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (query.uniqueResult() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean sendFriendRequest(Friend friend) {
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Friend getByRequest(int userId, int friendId) {
		return (Friend) sessionFactory.getCurrentSession()
				.createQuery("from Friend where userId='" + userId + "' and friendId='" + friendId + "'")
				.uniqueResult();
	}

	@Override
	public boolean rejectRequest(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean acceptRequest(Friend friend) {
		try {
			Friend friend2 = new Friend();
		    friend2.setUserId(friend.getFriendId());
			friend2.setFriendId(friend.getUserId());
			friend2.setStatus("A");
			sessionFactory.getCurrentSession().save(friend2);
			sessionFactory.getCurrentSession().update(friend);			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean unFriend(int userId, int friendId) {
		try {
			sessionFactory.getCurrentSession().delete(getByRequest(userId, friendId));
			sessionFactory.getCurrentSession().delete(getByRequest(friendId, userId));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}

}
