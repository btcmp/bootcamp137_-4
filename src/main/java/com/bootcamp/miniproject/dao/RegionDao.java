package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Region;

public interface RegionDao {

	public void save(Region region);
	
	public List<Region> selectAll();
	
	public Region getOne(Region region);
	
	public void update(Region region);
	
	public void saveUpdate(Region region);
	
	public void delete(Region region);

	public List<Region> getRegionByProvinceId(long id);
}
