package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Category;

//
@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(category);
		session.flush();
	}

	@Override
	public List<Category> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Category.class).list();
	}

	@Override
	public Category getOne(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.get(Category.class, category.getId());
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(category);
		session.flush();
	}

	@Override
	public void delete(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(category);
		session.flush();
	}

	@Override
	public List<Category> getCategoryBySearchName(String search) {
		// TODO Auto-generated method stub
		String hql = "from Category Cat where lower(Cat.name) like :search";
		Session session = sessionFactory.getCurrentSession();
		List<Category> categories = session.createQuery(hql).setParameter("search", "%"+search.toLowerCase()+"%").list();
		System.out.println(categories.size());
		if (categories.isEmpty()) {
			return null;
		} else {
			return categories;
		}
	}

}
