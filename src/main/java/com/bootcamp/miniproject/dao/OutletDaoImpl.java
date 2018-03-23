package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Outlet;

@Repository
public class OutletDaoImpl implements OutletDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Outlet outlet) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(outlet);
		session.flush();
	}

	@Override
	public List<Outlet> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Outlet.class).list();
	}

	@Override
	public Outlet getOne(Outlet outlet) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(Outlet.class, outlet.getId());
	}

	@Override
	public void update(Outlet outlet) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(outlet);
		session.flush();
	}

	@Override
	public void delete(Outlet outlet) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(outlet);
		session.flush();
	}

	@Override
	public List<Outlet> getOutletBySearchName(String search) {
		// TODO Auto-generated method stub
		String hql = "from Outlet Out where lower(Out.name) like:search";
		Session session = sessionFactory.getCurrentSession();
		List<Outlet> outlets = session.createQuery(hql).setParameter("search", "%"+search.toLowerCase()+"%").list();
		if(outlets.isEmpty()) {
			return null;
		} else {
			return outlets;			
		}
	}
	
}
