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
		
		
		List<EmployeeOutlet> employeeOutlets = employeeOutletDao.getEmployeeOutletByEmployee(employee);
		if(employeeOutlets!=null) {
			for(EmployeeOutlet employeeOutlet : employeeOutlets) {
				employeeOutletDao.delete(employeeOutlet);
			}
		}
		if(employee.getEmployeeOutlets()!=null) {
			for(EmployeeOutlet employeeOutlet : employee.getEmployeeOutlets()) {
				EmployeeOutlet empo = new EmployeeOutlet();
				empo.setEmployee(employee);
				empo.setOutlet(employeeOutlet.getOutlet());
				employeeOutletDao.save(empo);
			}
		}
		
		if(emp.getUser()!=null) {
			User user = new User();
			user.setId(emp.getUser().getId());
			user.setEmployee(employee);
			user.setActive(emp.getUser().isActive());
			user.setRole(emp.getUser().getRole());
			user.setUsername(emp.getUser().getUsername());
			user.setPassword(emp.getUser().getPassword());
			userDao.save(user);
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
