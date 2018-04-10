package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.SalesOrderDetail;
import com.bootcamp.miniproject.model.TransferStockDetail;

@Repository
public class SalesOrderDetailDaoImpl implements SalesOrderDetailDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(SalesOrderDetail salesOrderDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(salesOrderDetail);
		session.flush();
	}

	public List<SalesOrderDetail> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(SalesOrderDetail.class).list();
	}

	public SalesOrderDetail getOne(SalesOrderDetail salesOrderDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(SalesOrderDetail.class, salesOrderDetail.getId());
	}

	public void update(SalesOrderDetail salesOrderDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(salesOrderDetail);
		session.flush();
	}

	public void saveUpdate(SalesOrderDetail salesOrderDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(salesOrderDetail);
		session.flush();
	}

	public void delete(SalesOrderDetail salesOrderDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(salesOrderDetail);
		session.flush();
	}

	@Override
	public List<SalesOrderDetail> getSODBySOId(long id) {
		// TODO Auto-generated method stub
		System.out.println("111111");
		String hql = "from SalesOrderDetail SOD where SOD.salesOrder.id = :id";
		Session session = sessionFactory.getCurrentSession();
		List<SalesOrderDetail> salesOrderDetails = session.createQuery(hql).setParameter("id", id).list();
		System.out.println("22222");
		if (salesOrderDetails.isEmpty()) {
			return null;
		} else {
			return salesOrderDetails;
		}
	}
}
