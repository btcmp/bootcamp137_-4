package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.Category;
import com.bootcamp.miniproject.model.Item;
@Repository
public class ItemDaoImpl implements ItemDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Item> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Item i ORDER BY i.id";
		org.hibernate.Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public void save(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.save(item);
		session.flush();
	}

	@Override
	public void update(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.update(item);
		session.flush();
	}

	@Override
	public void delete(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(item);
	}

	@Override
	public Item getOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Item.class, id);
	}

	@Override
	public List<Item> getItemBySearchName(String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Item it where lower(it.name) like :nb";
		List<Item> items = session.createQuery(hql).setParameter("nb", "%"+search.toLowerCase()+"%").list();
		if (items.isEmpty()) {
			System.out.println("Kosong");
			return null;
		} else {
			System.out.println("Ada");
			return items;
		}
	}

	public List<Item> getItemByCategory(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ItemVariant inv where inv.item.category = :category and inv.active=1";
		List<Item> item = session.createQuery(hql).setParameter("category", category).list();
		if(item.isEmpty()) {
			return null;
		}
		return item;
	}

	
	
}
