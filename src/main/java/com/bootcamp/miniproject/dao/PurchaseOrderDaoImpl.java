package com.bootcamp.miniproject.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.PurchaseOrder;
//
@Repository
public class PurchaseOrderDaoImpl implements PurchaseOrderDao{
	@Autowired
	SessionFactory sessionFactory;
	
	public void save(PurchaseOrder po) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(po);
		session.flush();
	}

	public void update(PurchaseOrder po) {
		Session session = sessionFactory.getCurrentSession();
		session.clear();
		session.update(po);
		session.flush();
	}

	public void delete(PurchaseOrder po) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(po);
		session.flush();
	}

	public List<PurchaseOrder> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(PurchaseOrder.class).list();
	}

	public PurchaseOrder getOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.clear();
		return session.get(PurchaseOrder.class, id);
	}

	public void changeStatus(String status, long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update PurchaseOrder set status = :status where id = :id";
		session.createQuery(hql).setParameter("status", status).setParameter("id", id).executeUpdate();
	}

	public List<PurchaseOrder> searchPO(String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseOrder where prNo = :search or status = :search";
		List<PurchaseOrder> pos = session.createQuery(hql).setParameter("search", search).list();
		if(pos.isEmpty()) {
			return null;
		}else {
			return pos;
		}
	}
	
	public List<PurchaseOrder> searchPOByDate(Date startDate, Date endDate){
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseOrder where createdOn BETWEEN :start AND :end";
		List<PurchaseOrder> pos = session.createQuery(hql).setParameter("start", startDate)
				.setParameter("end", endDate).list();
		if(pos.isEmpty()) {
			return null;
		}else {
			return pos;
		}
	}
	
	public List<PurchaseOrder> searchPOByStatus(String search){
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseOrder where status = :status";
		List<PurchaseOrder> pos = session.createQuery(hql).setParameter("status", search).list();
		if(pos.isEmpty()) {
			return null;
		}else {
			return pos;
		}
	}
}
