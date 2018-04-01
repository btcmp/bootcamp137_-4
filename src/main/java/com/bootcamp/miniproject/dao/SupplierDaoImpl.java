package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Supplier;
@Repository
public class SupplierDaoImpl implements SupplierDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(Supplier supplier) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(supplier);
		session.flush();
	}

	public List<Supplier> selectAll() {
		// TODO Auto-generated method stub
		String hql = "from Supplier S order by S.id desc";
		Session session = sessionFactory.getCurrentSession();
		List<Supplier> suppliers = session.createQuery(hql).list();
		if (suppliers.isEmpty()) {
			return null;
		} else {
			return suppliers;
		}
	}

	public Supplier getOne(Supplier supplier) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(Supplier.class, supplier.getId());
	}

	public void update(Supplier supplier) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(supplier);
		session.flush();
	}

	public void saveUpdate(Supplier supplier) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(supplier);
		session.flush();
	}

	public void delete(Supplier supplier) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(supplier);
		session.flush();
	}
	
	public List<Supplier> getSupplierBySearchName(String search) {
		// TODO Auto-generated method stub
		String hql = "from Supplier S where lower(S.name) like :search";
		Session session = sessionFactory.getCurrentSession();
		List<Supplier> suppliers = session.createQuery(hql).setParameter("search", "%"+search.toLowerCase()+"%").list();
		if (suppliers.isEmpty()) {
			return null;
		} else {
			return suppliers;
		}
	}

	@Override
	public List<Supplier> selectStatusActive() {
		// TODO Auto-generated method stub
		String hql = "from Supplier S where S.active = 1 order by S.id desc";
		Session session = sessionFactory.getCurrentSession();
		List<Supplier> suppliers = session.createQuery(hql).list();
		if (suppliers.isEmpty()) {
			return null;
		} else {
			return suppliers;
		}
	}
}
