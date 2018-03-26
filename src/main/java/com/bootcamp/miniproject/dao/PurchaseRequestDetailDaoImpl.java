package com.bootcamp.miniproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.PurchaseRequestDetail;

@Repository
public class PurchaseRequestDetailDaoImpl implements PurchaseRequestDetailDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void save(PurchaseRequestDetail prDetail) {
		Session session = sessionFactory.getCurrentSession();
		session.save(prDetail);
		session.flush();
	}

	@Override
	public void update(PurchaseRequestDetail prDetail) {
		Session session = sessionFactory.getCurrentSession();
		session.update(prDetail);
		session.flush();
	}

	@Override
	public void delete(PurchaseRequestDetail prDetail) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(prDetail);
		session.flush();
	}

}
