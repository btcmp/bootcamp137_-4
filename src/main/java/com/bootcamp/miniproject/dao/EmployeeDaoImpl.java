package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
		session.flush();
	}

	@Override
	public List<Employee> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Employee.class).list();
	}

	@Override
	public Employee getOne(Employee employee) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(Employee.class, employee.getId());
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(employee);
		session.flush();
	}

	@Override
	public void delete(Employee employee) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(employee);
		session.flush();
	}

	@Override
	public List<Employee> selectStatusActive() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Employee emp where emp.active = 1";
		List<Employee> employees = session.createQuery(hql).list();
		if(employees.isEmpty()) {
			return null;
		} else {
			return employees;
		}
	}
	
	@Override
	public List<Employee> getOneByUsername(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Employee emp where emp.user.username like :username";
		List<Employee> employee = session.createQuery(hql).setParameter("username", username).list();
		return employee;
	}
	//
}
