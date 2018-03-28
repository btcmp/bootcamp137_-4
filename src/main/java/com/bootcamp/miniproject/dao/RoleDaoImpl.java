package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
		session.flush();
	}

	@Override
	public List<Role> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Role.class).list();
	}

	@Override
	public Role getOne(Role role) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(Role.class, role.getId());
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(role);
		session.flush();
	}

	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(role);
		session.flush();
	}
	
	
}
