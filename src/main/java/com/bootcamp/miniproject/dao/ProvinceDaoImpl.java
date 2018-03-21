package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Province;

@Repository
public class ProvinceDaoImpl implements ProvinceDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(Province province) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(province);
		session.flush();
	}

	public List<Province> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Province.class).list();
	}

	public Province getOne(Province province) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(Province.class, province.getId());
	}

	public void update(Province province) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(province);
		session.flush();
	}

	public void saveUpdate(Province province) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(province);
		session.flush();
	}

	public void delete(Province province) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(province);
		session.flush();
	}
}
