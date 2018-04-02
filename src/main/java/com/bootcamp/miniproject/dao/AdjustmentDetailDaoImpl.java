package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.AdjustmentDetail;

@Repository
public class AdjustmentDetailDaoImpl implements AdjustmentDetailDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(AdjustmentDetail adjustmentDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(adjustmentDetail);
		session.flush();
	}

	@Override
	public List<AdjustmentDetail> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(AdjustmentDetail.class).list();
	}

	@Override
	public AdjustmentDetail getOne(AdjustmentDetail adjustmentDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(AdjustmentDetail.class, adjustmentDetail.getId());
	}

	@Override
	public void update(AdjustmentDetail adjustmentDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(adjustmentDetail);
		session.flush();
	}

	@Override
	public void delete(AdjustmentDetail adjustmentDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(adjustmentDetail);
		session.flush();
	}
	
}
