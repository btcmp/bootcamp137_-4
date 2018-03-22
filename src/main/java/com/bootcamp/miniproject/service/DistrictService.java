package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.DistrictDao;
import com.bootcamp.miniproject.model.District;
@Service
@Transactional
public class DistrictService {

	@Autowired
	DistrictDao districtDao;
	
	public void save(District district) {
		districtDao.save(district);
	}
	
	public void delete(District district) {
		districtDao.delete(district);
	}
	
	public void update(District district) {
		districtDao.update(district);
	}
	
	public List<District> selectAll(){
		return districtDao.selectAll();
	}
	
	public void saveUpdate(District district) {
		districtDao.saveUpdate(district);
	}
	
	public District getOne(long id) {
		District district = new District();
		district.setId(id);
		return districtDao.getOne(district);
	}

	public List<District> getDistrictByRegionId(long id) {
		// TODO Auto-generated method stub
		return districtDao.getRegionByRegionId(id)	;
	}
}
