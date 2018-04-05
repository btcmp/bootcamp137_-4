package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.AdjustmentHistory;

@Repository
public class AdjustmentHistoryDaoImpl implements AdjustmentHistoryDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(AdjustmentHistory adjustmentHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(adjustmentHistory);
		session.flush();
		
	}

	@Override
	public List<AdjustmentHistory> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(AdjustmentHistory.class).list();
	}

	@Override
	public AdjustmentHistory getOne(AdjustmentHistory adjustmentHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(AdjustmentHistory.class, adjustmentHistory.getId());
	}

	@Override
	public void update(AdjustmentHistory adjustmentHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(adjustmentHistory);
		session.flush();
		
	}

	@Override
	public void delete(AdjustmentHistory adjustmentHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(adjustmentHistory);
		session.flush();
	}

	@Override
	public List<AdjustmentHistory> getAdjustmentHistoryByAdjustmentId(long search) {
		// TODO Auto-generated method stub
		String hql = "from AdjustmentHistory adjustHis where adjustHis.adjustment.id = :search";
		Session session = sessionFactory.getCurrentSession();
		List<AdjustmentHistory> adjustmentHistories = session.createQuery(hql).setParameter("search", search).list();
		if(adjustmentHistories.isEmpty()) {
			return null;
		} else {
			return adjustmentHistories;
		}
	}
	
}
