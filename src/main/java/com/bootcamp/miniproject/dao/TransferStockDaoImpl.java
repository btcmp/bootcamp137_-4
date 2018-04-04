package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.TransferStock;

@Repository
public class TransferStockDaoImpl implements TransferStockDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(TransferStock transferStock) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(transferStock);
		session.flush();
	}

	public List<TransferStock> selectAll() {
		// TODO Auto-generated method stub
		String hql = "from TransferStock";
		Session session = sessionFactory.getCurrentSession();
		List<TransferStock> transferStocks = session.createQuery(hql).list();
		if (transferStocks.isEmpty()) {
			return null;
		} else {
			return transferStocks;
		}
	}

	public TransferStock getOne(TransferStock transferStock) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(TransferStock.class, transferStock.getId());
	}

	public void update(TransferStock transferStock) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(transferStock);
		session.flush();
	}

	public void saveUpdate(TransferStock transferStock) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(transferStock);
		session.flush();
	}

	public void delete(TransferStock transferStock) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(transferStock);
		session.flush();
	}
	
	public List<TransferStock> getTransferStockByOutletId(long search) {
		// TODO Auto-generated method stub
		String hql = "from TransferStock TS where TS.toOutlet.id = :search";
		Session session = sessionFactory.getCurrentSession();
		List<TransferStock> transferStocks = session.createQuery(hql).setParameter("search", search).list();
		if (transferStocks.isEmpty()) {
			return null;
		} else {
			return transferStocks;
		}
	}
	
	public List<TransferStock> getTransferStockByFromOutletId(long search) {
		// TODO Auto-generated method stub
		String hql = "from TransferStock TS where TS.fromOutlet.id = :search";
		Session session = sessionFactory.getCurrentSession();
		List<TransferStock> transferStocks = session.createQuery(hql).setParameter("search", search).list();
		if (transferStocks.isEmpty()) {
			return null;
		} else {
			return transferStocks;
		}
	}
}
