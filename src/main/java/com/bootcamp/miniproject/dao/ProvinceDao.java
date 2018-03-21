package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Province;

public interface ProvinceDao {

	public void save(Province province);
	
	public List<Province> selectAll();
	
	public Province getOne(Province province);
	
	public void update(Province province);
	
	public void saveUpdate(Province province);
	
	public void delete(Province province);
}
