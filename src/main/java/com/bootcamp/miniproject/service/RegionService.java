package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.RegionDao;
import com.bootcamp.miniproject.model.Region;

@Service
@Transactional
public class RegionService {

	@Autowired
	RegionDao regionDao;
	
	public void save(Region region) {
		regionDao.save(region);
	}
	
	public void delete(Region region) {
		regionDao.delete(region);
	}
	
	public void update(Region region) {
		regionDao.update(region);
	}
	
	public List<Region> selectAll(){
		return regionDao.selectAll();
	}
	
	public void saveUpdate(Region region) {
		regionDao.saveUpdate(region);
	}
	
	public Region getOne(long id) {
		Region region = new Region();
		region.setId(id);
		return regionDao.getOne(region);
	}
}
