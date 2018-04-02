package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseOrderDetail;

@Repository
public class PurchaseOrderDetailDaoImpl implements PurchaseOrderDetailDao{
	@Autowired
	SessionFactory sessionFactory;
	
	public void save(PurchaseOrderDetail poDetail) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(poDetail);
		session.flush();
	}

	public void update(PurchaseOrderDetail poDetail) {
		Session session = sessionFactory.getCurrentSession();
		session.update(poDetail);
		session.flush();
	}

	public void delete(PurchaseOrderDetail poDetail) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(poDetail);
		session.flush();
	}

	public List<PurchaseOrderDetail> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(PurchaseOrderDetail.class).list();
	}

	public PurchaseOrderDetail getOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(PurchaseOrderDetail.class, id);
	}

	public List<PurchaseOrderDetail> getDetailByPO(PurchaseOrder po) {
		Session session = sessionFactory.getCurrentSession();
		List<PurchaseOrderDetail> detail = session.createCriteria(PurchaseOrderDetail.class).add(Restrictions.eq("purchaseOrder.id", po.getId())).list(); 
 		if(detail.isEmpty()) {
 			return null;
 		}else {
 			return detail;
 		}
	}
}
//