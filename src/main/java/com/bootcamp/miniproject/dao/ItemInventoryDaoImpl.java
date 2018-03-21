package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.miniproject.model.ItemInventory;

public class ItemInventoryDaoImpl implements ItemInventoryDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<ItemInventory> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ItemInventory i ORDER BY i.id";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public void save(ItemInventory itemInventory) {
		Session session = sessionFactory.getCurrentSession();
		session.save(itemInventory);
		session.flush();
	}

	@Override
	public ItemInventory getOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(ItemInventory.class, id);
	}

	@Override
	public void delete(ItemInventory itemInventory) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(itemInventory);
		session.flush();
	}

	@Override
	public void update(ItemInventory itemInventory) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(itemInventory);
		session.flush();
	}

}
