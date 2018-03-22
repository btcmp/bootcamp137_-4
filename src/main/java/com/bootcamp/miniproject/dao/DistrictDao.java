package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.District;

public interface DistrictDao {

	public void save(District district);
	
	public List<District> selectAll();
	
	public District getOne(District district);
	
	public void update(District district);
	
	public void saveUpdate(District district);
	
	public void delete(District district);

	public List<District> getRegionByRegionId(long id);
}
