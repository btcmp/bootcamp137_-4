package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.EmployeeOutlet;

@Repository
public class EmployeeOutletDaoImpl implements EmployeeOutletDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(EmployeeOutlet employeeOutlet) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(employeeOutlet);
		session.flush();
	}
	
	@Override
	public void saveOrUpdate(EmployeeOutlet employeeOutlet) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(employeeOutlet);
		session.flush();
	}
	

	@Override
	public List<EmployeeOutlet> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(EmployeeOutlet.class).list();
	}

	@Override
	public EmployeeOutlet getOne(EmployeeOutlet employeeOutlet) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(EmployeeOutlet.class, employeeOutlet.getId());
	}

	@Override
	public void update(EmployeeOutlet employeeOutlet) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(employeeOutlet);
		session.flush();
	}

	@Override
	public void delete(EmployeeOutlet employeeOutlet) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(employeeOutlet);
		session.flush();
	}

	@Override
	public List<EmployeeOutlet> getEmployeeOutletByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<EmployeeOutlet> empos = session.createCriteria(EmployeeOutlet.class).add(Restrictions.eq("employee.id", employee.getId())).list(); 
 		if(empos.isEmpty()) {
 			return null;
 		}
		return empos;
	}

	@Override
	public void deleteByEmployeeId(long employeeId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from EmployeeOutlet where employee.id = :employeeId";
		session.createQuery(hql).setParameter("employeeId", employeeId);
	}



	
}
