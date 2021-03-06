package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.ItemInventory;
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
		List<PurchaseRequest> pr = query.list();
		
		if (pr.isEmpty()) {
			return null;
		} else {
			return pr;
		}
	}
	//
	@Override
	public List<PurchaseRequest> getAllPrByOutlet(Long outletId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM PurchaseRequest pr WHERE pr.outlet.id =:outletId";
		Query query = session.createQuery(hql).setParameter("outletId", outletId);
		List<PurchaseRequest> pr = query.list();
		if (pr.isEmpty()) {
			return null;
		} else {
			return pr;
		}
	}

	@Override
	public PurchaseRequest getOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		PurchaseRequest pr = session.get(PurchaseRequest.class, id);
		if (pr == null) {
			return null;
		} else {
			return pr;
		}
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
		session.clear();
		session.update(pr);
		session.flush();
	}

	@Override
	public void delete(PurchaseRequest pr) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(pr);
		session.flush();
	}
	
	public List<PurchaseRequest> searchPR(String search) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseRequest where lower(prNo) = :search or lower(status) = :search or lower(notes) = :search";
		List<PurchaseRequest> prs = session.createQuery(hql).setParameter("search", "%"+search.toLowerCase()+"%").list();
		if(prs.isEmpty()) {
			return null;
		}else {
			return prs;
		}
	}
	
	public List<PurchaseRequest> searchPRByStatus(Long outletId, String search){
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseRequest pr where pr.status = :status AND pr.outlet.id =:outletId";
		List<PurchaseRequest> prs = session.createQuery(hql).setParameter("outletId", outletId).setParameter("status", search).list();
		if(prs.isEmpty()) {
			return null;
		}else {
			return prs;
		}
	}
	
	public void approve(long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update PurchaseRequest set status='Approved' where id = :id";
		session.createQuery(hql).setParameter("id", id).executeUpdate();
	}

	public void reject(long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update PurchaseRequest set status='Rejected' where id = :id";
		session.createQuery(hql).setParameter("id", id).executeUpdate();
	}

	public void createPo(long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update PurchaseRequest set status='PO Created' where id = :id";
		session.createQuery(hql).setParameter("id", id).executeUpdate();
	}
	
	public int CountPRByMonth(int month, int year) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseRequest where MONTH(createdOn) = :month and YEAR(createdOn) = :year";
		List<PurchaseRequest> prList = session.createQuery(hql).setParameter("month", month).setParameter("year", year).list();
		System.out.println(prList.size());
		if(prList.isEmpty()) {
			return 0;
		}
		return prList.size();
	}
	
	@Override
	public List<PurchaseRequest> searchPR(Long outletId, String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from PurchaseRequest pr where pr.outlet.id =:outletId and (lower(pr.prNo) like :keyword or lower(pr.notes) like :keyword or lower(pr.status) like :keyword)";
		List<PurchaseRequest> pr = session.createQuery(hql).setParameter("outletId", outletId).setParameter("keyword", "%"+search.toLowerCase()+"%").list();
		if (pr.isEmpty()) {
			return null;
		} else {
			return pr;
		}
	}
}
