package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.PurchaseRequest;

@Repository
public class PurchaseRequestDaoImpl implements PurchaseRequestDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<PurchaseRequest> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM PurchaseRequest";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public PurchaseRequest getOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(PurchaseRequest.class, id);
	}

	@Override
	public void save(PurchaseRequest pr) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pr);
		session.flush();
	}

	@Override
	public void update(PurchaseRequest pr) {
		Session session = sessionFactory.getCurrentSession();
		session.update(pr);
		session.flush();
	}

	@Override
	public void delete(PurchaseRequest pr) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(pr);
		session.flush();
	}
	
}
