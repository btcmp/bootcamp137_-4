package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Outlet;

public interface OutletDao {
	
	public void save(Outlet outlet);
	public List<Outlet> selectAll();
	public Outlet getOne(Outlet outlet);
	public void update(Outlet outlet);
	public void delete(Outlet outlet);
	public List<Outlet> getOutletBySearchName(String search);
	public List<Outlet> selectStatusActive();
	
}
