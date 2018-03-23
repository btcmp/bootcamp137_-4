package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Item;
import com.bootcamp.miniproject.model.ItemInventory;

@Repository
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
//
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

	@Override
	public List<ItemInventory> searchItemInventoryByItemName(String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ItemInventory in where lower(in.itemVarian.item.name) like :itemName";
		List<ItemInventory> itemInventorys = session.createQuery(hql).setParameter("itemName", "%"+search.toLowerCase()+"%").list();
		if (itemInventorys.isEmpty()) {
			return null;
		} else {
			return itemInventorys;
		}
	}

}
