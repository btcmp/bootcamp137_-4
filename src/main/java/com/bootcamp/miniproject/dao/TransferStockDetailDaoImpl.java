package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.TransferStockDetail;
@Repository
public class TransferStockDetailDaoImpl implements TransferStockDetailDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(TransferStockDetail transferStockDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(transferStockDetail);
		session.flush();
	}

	public List<TransferStockDetail> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(TransferStockDetail.class).list();
	}

	public TransferStockDetail getOne(TransferStockDetail transferStockDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(TransferStockDetail.class, transferStockDetail.getId());
	}

	public void update(TransferStockDetail transferStockDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(transferStockDetail);
		session.flush();
	}

	public void saveUpdate(TransferStockDetail transferStockDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(transferStockDetail);
		session.flush();
	}

	public void delete(TransferStockDetail transferStockDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(transferStockDetail);
		session.flush();
	}

	@Override
	public List<TransferStockDetail> getTransferStockByTransferStockId(long search) {
		// TODO Auto-generated method stub
		String hql = "from TransferStockDetail TSD where TSD.transferStock.id = :search";
		Session session = sessionFactory.getCurrentSession();
		List<TransferStockDetail> transferStockDetails = session.createQuery(hql).setParameter("search", search).list();
		if (transferStockDetails.isEmpty()) {
			return null;
		} else {
			return transferStockDetails;
		}
	}
}
