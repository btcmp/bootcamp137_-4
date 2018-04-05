package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Adjustment;

@Repository
public class AdjustmentDaoImpl implements AdjustmentDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Adjustment adjustment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(adjustment);
		session.flush();
	}

	@Override
	public List<Adjustment> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Adjustment.class).list();
	}

	@Override
	public Adjustment getOne(Adjustment adjustment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(Adjustment.class, adjustment.getId());
	}

	@Override
	public void update(Adjustment adjustment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(adjustment);
		session.flush();
	}

	@Override
	public void delete(Adjustment adjustment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(adjustment);
		session.flush();
	}

	@Override
	public List<Adjustment> getAdjustmentIdByOutletId(long outletId) {
		// TODO Auto-generated method stub
		String hql = "from Adjustment adjust where adjust.outlet.id = :outletId";
		Session session = sessionFactory.getCurrentSession();
		List<Adjustment> adjust = session.createQuery(hql).setParameter("outletId", outletId).list();
		return adjust;
	}
	
	
}
