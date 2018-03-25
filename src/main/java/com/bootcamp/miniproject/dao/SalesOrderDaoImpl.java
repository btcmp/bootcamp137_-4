package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.SalesOrder;

@Repository
public class SalesOrderDaoImpl implements SalesOrderDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(salesOrder);
		session.flush();
	}

	public List<SalesOrder> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(SalesOrder.class).list();
	}

	public SalesOrder getOne(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(SalesOrder.class, salesOrder.getId());
	}

	public void update(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(salesOrder);
		session.flush();
	}

	public void saveUpdate(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(salesOrder);
		session.flush();
	}

	public void delete(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(salesOrder);
		session.flush();
	}
}
