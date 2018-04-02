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
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		emp.setTitle(employee.getTitle());
		emp.setHaveAccount(employee.isHaveAccount());
		emp.setActive(employee.isActive());
		employeeDao.save(emp);
		
		System.out.println(employee.getEmployeeOutlets());
	
		if(!employee.getEmployeeOutlets().isEmpty()) {
			for(EmployeeOutlet employeeOutlet : employee.getEmployeeOutlets()) {
				EmployeeOutlet empo = new EmployeeOutlet();
				empo.setEmployee(emp);
				empo.setOutlet(employeeOutlet.getOutlet());
				employeeOutletDao.save(empo);
			}
		}
		
		if(employee.isHaveAccount()) {
			User user = new User();
			user.setEmployee(emp);
			user.setActive(employee.getUser().isActive());
			user.setRole(employee.getUser().getRole());
			user.setUsername(employee.getUser().getUsername());
			user.setPassword(employee.getUser().getPassword());
			userDao.save(user);
		}
	}

	
	public void update(Employee employee) {
//		employeeDao.update(employee);
		Employee emp = new Employee();
		emp.setId(employee.getId());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		emp.setTitle(employee.getTitle());
		emp.setHaveAccount(employee.isHaveAccount());
		emp.setActive(employee.isActive());
		employeeDao.update(emp);

		
		
		List<EmployeeOutlet> empout = employeeOutletDao.getEmployeeOutletByEmployee(emp);
		if(empout != null ) {
			for(EmployeeOutlet eo : empout) {
				employeeOutletDao.delete(eo);
			}
		}
		
		if(!employee.getEmployeeOutlets().isEmpty()) {
			for(EmployeeOutlet employeeOutlet : employee.getEmployeeOutlets()) {
				EmployeeOutlet empo = new EmployeeOutlet();
				empo.setEmployee(emp);
				empo.setOutlet(employeeOutlet.getOutlet());
				employeeOutletDao.save(empo);
			}
		}
		
		if(employee.isHaveAccount()) {
			User user = new User();
			user.setId(employee.getUser().getId());
			user.setEmployee(emp);
			user.setActive(employee.getUser().isActive());
			user.setRole(employee.getUser().getRole());
			user.setUsername(employee.getUser().getUsername());
			user.setPassword(employee.getUser().getPassword());
			userDao.saveOrUpdate(user);
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
	
	
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

	public List<Employee> selectStatusActive() {
		// TODO Auto-generated method stub
		return employeeDao.selectStatusActive();
	}
	
	public List<Employee> getOneByUsername(String username) {
		// TODO Auto-generated method stub
		return employeeDao.getOneByUsername(username);
	}


	public void updateDelete(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.update(employee);
	}
	
}
