package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ProvinceDao;
import com.bootcamp.miniproject.model.Province;

@Service
@Transactional
public class ProvinceService {

	@Autowired
	ProvinceDao provinceDao;
	
	public void save(Province province) {
		provinceDao.save(province);
	}
	
	public void delete(Province province) {
		provinceDao.delete(province);
	}
	
	public void update(Province province) {
		provinceDao.update(province);
	}
	
	public List<Province> selectAll(){
		return provinceDao.selectAll();
	}
	
	public void saveUpdate(Province province) {
		provinceDao.saveUpdate(province);
	}
	
	public Province getOne(long id) {
		Province province = new Province();
		province.setId(id);
		return provinceDao.getOne(province);
	}
}
