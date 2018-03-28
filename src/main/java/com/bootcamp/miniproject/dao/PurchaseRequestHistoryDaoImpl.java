package com.bootcamp.miniproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.PurchaseRequestHistory;

@Repository
public class PurchaseRequestHistoryDaoImpl implements PurchaseRequestHistoryDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(PurchaseRequestHistory prHist) {
		Session session = sessionFactory.getCurrentSession();
		session.save(prHist);
		session.flush();
	}

	@Override
	public void update(PurchaseRequestHistory prHist) {
		Session session = sessionFactory.getCurrentSession();
		session.update(prHist);
		session.flush();
	}

}
