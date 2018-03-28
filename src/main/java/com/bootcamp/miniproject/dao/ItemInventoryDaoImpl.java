package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.ItemInventory;

@Repository
public class ItemInventoryDaoImpl implements ItemInventoryDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<ItemInventory> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ItemInventory i WHERE i.itemVariant.active = true ORDER BY i.id DESC";
		Query query = session.createQuery(hql);
		List<ItemInventory> inventory = query.list();
		if (inventory.isEmpty()) {
			return null;
		} else {
			return inventory;
		}
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
		ItemInventory inventory = session.get(ItemInventory.class, id);
		if(inventory == null) {
			return null;
		} else {
			return inventory;
		}
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
		session.update(itemInventory);
		session.flush();
	}

	@Override
	public List<ItemInventory> searchItemInventoryByItemName(String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ItemInventory i where lower(i.itemVariant.item.name) like :itemName or lower(i.itemVariant.name) like :itemName";
		List<ItemInventory> itemInventorys = session.createQuery(hql).setParameter("itemName", "%"+search.toLowerCase()+"%").list();
		if (itemInventorys.isEmpty()) {
			return null;
		} else {
			return itemInventorys;
		}
	}
	@Override
	public List<ItemInventory> getInventoryByItemId(long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ItemInventory inv where inv.itemVariant.item.id =:id and inv.itemVariant.active = 1 and inv.itemVariant.item.active = 1";
		List<ItemInventory> itemInventory = session.createQuery(hql).setParameter("id", id).list();
		if (itemInventory.isEmpty()) {
			System.out.println("Kosong");
			return null;
		} else {
			System.out.println("Ada");
			return itemInventory;
		}
	}

}
