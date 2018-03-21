package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Region;

@Repository
public class RegionDaoImpl implements RegionDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(Region region) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(region);
		session.flush();
	}

	public List<Region> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Region.class).list();
	}

	public Region getOne(Region region) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(Region.class, region.getId());
	}

	public void update(Region region) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(region);
		session.flush();
	}

	public void saveUpdate(Region region) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(region);
		session.flush();
	}

	public void delete(Region region) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(region);
		session.flush();
	}
}
