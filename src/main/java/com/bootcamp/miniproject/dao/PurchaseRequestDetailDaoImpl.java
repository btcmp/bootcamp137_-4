package com.bootcamp.miniproject.dao;

import java.util.List;

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

	@Override
	public List<PurchaseRequestDetail> getPRDetailByPRIdandOutletId(Long prId, Long outletId) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(prId);
		System.out.println(outletId);
		String hql = "FROM PurchaseRequestDetail prd WHERE prd.purchaseRequest.id =:prId AND prd.purchaseRequest.outlet.id =:outletId ";
		List<PurchaseRequestDetail> prDetail = session.createQuery(hql).setParameter("prId", prId).setParameter("outletId", outletId).list();
		if (prDetail.isEmpty()) {
			return null;
		} else {
			return prDetail;
		}	
	}

}
