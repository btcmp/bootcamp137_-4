package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.EmployeeDao;
import com.bootcamp.miniproject.dao.EmployeeOutletDao;
import com.bootcamp.miniproject.dao.UserDao;
import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.EmployeeOutlet;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.User;
//
@Service
@Transactional
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	EmployeeOutletDao employeeOutletDao;
	
	public void save(Employee employee) {
		Employee emp = new Employee();
		emp.setId(employee.getId());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		emp.setTitle(employee.getTitle());
		emp.setHaveAccount(employee.isHaveAccount());
		emp.setActive(employee.isActive());
		employeeDao.save(emp);
		
		if (employee.isHaveAccount() == true) {

			for(User users : employee.getUsers()) {
				User user = new User();
				user.setEmployee(emp);
				user.setRole(users.getRole());
				user.setUsername(users.getUsername());
				user.setPassword(users.getPassword());
				userDao.save(user);
			} 	

		}
		
		for(EmployeeOutlet employeeOutlets : employee.getEmployeeOutlets()) {
			Outlet outlet = new Outlet();
			outlet.setId(employeeOutlets.getOutlet().getId());
			EmployeeOutlet employeeOutlet = new EmployeeOutlet();
			employeeOutlet.setOutlet(outlet);
			employeeOutlet.setEmployee(emp);
			employeeOutletDao.save(employeeOutlet);
		}
		
		
	}
	
	public List<Employee> selectAll(){
		return employeeDao.selectAll();
	}
	
	public Employee getOne(long id) {
		Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstName("0");
		employee.setLastName("0");
		employee.setHaveAccount(false);
		employee.setActive(false);
		return employeeDao.getOne(employee);
	}
	
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
	
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

	public List<Employee> selectStatusActive() {
		// TODO Auto-generated method stub
		return employeeDao.selectStatusActive();
	}
	
}
