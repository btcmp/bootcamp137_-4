package com.bootcamp.miniproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.miniproject.model.ItemVariant;
@Repository
public class ItemVariantDaoImpl implements ItemVariantDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<ItemVariant> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From ItemVariant i where i.active = true ORDER BY i.id";
		org.hibernate.Query query = session.createQuery(hql);
		List<ItemVariant> variant = query.list();
		if (variant.isEmpty()) {
			return null;
		} else {
			return variant;
		}
	}

	@Override
	public ItemVariant getOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		ItemVariant variant = session.get(ItemVariant.class, id);
		if (variant == null) {
			return null;
		} else {
			return variant;
		}
	}

	@Override
	public void save(ItemVariant itemVariant) {
		Session session = sessionFactory.getCurrentSession();
		session.save(itemVariant);
		session.flush();
	}

	@Override
	public List<ItemVariant> getItemVariantBySearchName(String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ItemVariant it where lower(it.name) like :nb";
		List<ItemVariant> items = session.createQuery(hql).setParameter("nb", "%"+search.toLowerCase()+"%").list();
		if (items.isEmpty()) {
			System.out.println("Kosong");
			return null;
		} else {
			System.out.println("Ada");
			return items;
		}
	}

	@Override
	public void delete(ItemVariant itemVariant) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(itemVariant);
		session.flush();
	}

	@Override
	public void update(ItemVariant itemVariant) {
		Session session = sessionFactory.getCurrentSession();		
		System.out.println("menggunakan update "+ itemVariant.getId());
		session.update(itemVariant);
		session.flush();
	}

	@Override
	public List<ItemVariant> getVariantByItemId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ItemVariant var where var.item.id =:id";
		List<ItemVariant> var = session.createQuery(hql).setParameter("id", id).list();
		if (var.isEmpty()) {
			System.out.println("Kosong");
			return null;
		} else {
			System.out.println("Ada");
			return var;
		}
	}

}
