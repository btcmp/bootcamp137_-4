package com.bootcamp.miniproject.dao;

import java.util.List;

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

	@Override
	public List<PurchaseRequestHistory> getPRHistoryByPr(Long prId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From PurchaseRequestHistory prh Where prh.purchaseRequest.id =:prId";
		List<PurchaseRequestHistory> prHistory = session.createQuery(hql).setParameter("prId", prId).list();
		if (prHistory.isEmpty()) {
			return null;
		} else {
			return prHistory;
		}
	}
	
	
//
}
