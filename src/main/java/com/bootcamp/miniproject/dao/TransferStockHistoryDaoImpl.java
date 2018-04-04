package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.TransferStockHistory;

@Repository
public class TransferStockHistoryDaoImpl implements TransferStockHistoryDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(TransferStockHistory transferStockHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(transferStockHistory);
		session.flush();
	}

	public List<TransferStockHistory> selectAll() {
		// TODO Auto-generated method stub
		String hql = "from TransferStockHistory";
		Session session = sessionFactory.getCurrentSession();
		List<TransferStockHistory> transferStockHistorys = session.createQuery(hql).list();
		if (transferStockHistorys.isEmpty()) {
			return null;
		} else {
			return transferStockHistorys;
		}
	}

	public TransferStockHistory getOne(TransferStockHistory transferStockHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(TransferStockHistory.class, transferStockHistory.getId());
	}

	public void update(TransferStockHistory transferStockHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(transferStockHistory);
		session.flush();
	}

	public void saveUpdate(TransferStockHistory transferStockHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(transferStockHistory);
		session.flush();
	}

	public void delete(TransferStockHistory transferStockHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(transferStockHistory);
		session.flush();
	}
	
	public List<TransferStockHistory> getTransferStockHistoryByTransferStockId(long search) {
		// TODO Auto-generated method stub
		String hql = "from TransferStockHistory TSH where TSH.transferStock.id = :search order by TSH.id asc";
		Session session = sessionFactory.getCurrentSession();
		List<TransferStockHistory> transferStockHistorys = session.createQuery(hql).setParameter("search", search).list();
		if (transferStockHistorys.isEmpty()) {
			return null;
		} else {
			return transferStockHistorys;
		}
	}
}
