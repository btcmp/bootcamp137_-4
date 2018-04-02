package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.EmployeeOutletDao;
import com.bootcamp.miniproject.model.EmployeeOutlet;

@Service
@Transactional
public class EmployeeOutletService {

	@Autowired
	EmployeeOutletDao employeeOutletDao;
	
	public void save(EmployeeOutlet employeeOutlet) {
		employeeOutletDao.save(employeeOutlet);
	}
	
	public void saveOrUpdate(EmployeeOutlet employeeOutlet) {
		employeeOutletDao.saveOrUpdate(employeeOutlet);
	}
	
	public List<EmployeeOutlet> selectAll(){
		return employeeOutletDao.selectAll();
	}
	
	public EmployeeOutlet getOne(long id) {
		EmployeeOutlet employeeOutlet = new EmployeeOutlet();
		employeeOutlet.setId(id);
		return employeeOutletDao.getOne(employeeOutlet);
	}
	
	public void update(EmployeeOutlet employeeOutlet) {
		employeeOutletDao.update(employeeOutlet);
	}
	
	public void delete(EmployeeOutlet employeeOutlet) {
		employeeOutletDao.delete(employeeOutlet);
	}
	
	public void deleteByEmployeeId(long employeeId) {
		employeeOutletDao.deleteByEmployeeId(employeeId);
	}

	
	
}
