package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.District;

@Repository
public class DistrictDaoImpl implements DistrictDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(District district) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(district);
		session.flush();
	}

	public List<District> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(District.class).list();
	}

	public District getOne(District district) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(District.class, district.getId());
	}

	public void update(District district) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(district);
		session.flush();
	}

	public void saveUpdate(District district) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(district);
		session.flush();
	}

	public void delete(District district) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(district);
		session.flush();
	}
	
	@Override
	public List<District> getRegionByRegionId(long id) {
		// TODO Auto-generated method stub
		String hql = "from District dis where dis.region.id = :id";
		Session session = sessionFactory.getCurrentSession();
		List<District> districts = session.createQuery(hql).setParameter("id", id).list();
		if (districts.isEmpty()) {
			return null;
		} else {
			return districts;
		}
	}
}
