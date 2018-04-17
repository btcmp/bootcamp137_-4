package com.bootcamp.miniproject.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseRequest;
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
		PurchaseOrder po = session.get(PurchaseOrder.class, id);
		session.clear();
		return po;
//		if (po == null) {
//			return null;
//		} else {
//			return po;
//		}
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
		List<PurchaseOrder> poList = session.createQuery(hql).setParameter("status", search).list();
		if(poList.isEmpty()) {
			return null;
		}else {
			return poList;
		}
	}
	
	public int CountPRByMonth(int month, int year) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseOrder where MONTH(createdOn) =:month and YEAR(createdOn) =:year";
		List<PurchaseOrder> poList = session.createQuery(hql).setParameter("month", month).setParameter("year", year).list();
		if(poList.isEmpty()) {
			return 0;
		}
		return poList.size();
	}

	@Override
	public void approve(long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update PurchaseOrder set status='Approved' where id = :id";
		session.createQuery(hql).setParameter("id", id).executeUpdate();
	}

	@Override
	public void reject(long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update PurchaseOrder set status='Rejected' where id = :id";
		session.createQuery(hql).setParameter("id", id).executeUpdate();
	}

	@Override
	public List<PurchaseOrder> getAllPOByOutlet(Long outletId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM PurchaseOrder pr WHERE pr.outlet.id =:outletId";
		Query query = session.createQuery(hql).setParameter("outletId", outletId);
		List<PurchaseOrder> po = query.list();
		session.flush();
		if (po.isEmpty()) {
			return null;
		} else {
			return po;
		}
	}

	@Override
	public void process(long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update PurchaseOrder set status='Processed' where id = :id";
		session.createQuery(hql).setParameter("id", id).executeUpdate();
	}

	@Override
	public List<PurchaseOrder> getPOByStatus(Long outletId, String status) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseOrder po where po.status = :status AND po.outlet.id =:outletId";
		List<PurchaseOrder> pos = session.createQuery(hql).setParameter("outletId", outletId).setParameter("status", status).list();
		if(pos.isEmpty()) {
			return null;
		}else {
			return pos;
		}
	}
	
	@Override
	public List<PurchaseOrder> searchPO(Long outletId, String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseOrder po where po.outlet.id =:outletId and (lower(po.poNo) like :keyword or lower(po.notes) like :keyword or lower(po.status) like :keyword or lower(po.supplier.name) like :keyword)";
		List<PurchaseOrder> po = session.createQuery(hql).setParameter("outletId", outletId).setParameter("keyword", "%"+search.toLowerCase()+"%").list();
		if (po.isEmpty()) {
			return null;
		} else {
			return po;
		}
	}
}
