package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		session.flush();
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(User.class).list();
	}

	@Override
	public User getOne(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, user.getId());
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		session.flush();
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
		session.flush();
	}

	@Override
	public User getUserByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User where employee = :employee";
		List<User> user = session.createQuery(hql).setParameter("employee", employee).list();
		if(user.isEmpty()) {
			return null;
		}
		return user.get(0);
	}
	
}
