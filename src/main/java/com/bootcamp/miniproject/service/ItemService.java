package com.bootcamp.miniproject.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ItemDao;
import com.bootcamp.miniproject.dao.ItemInventoryDao;
import com.bootcamp.miniproject.dao.ItemVariantDao;
import com.bootcamp.miniproject.dao.OutletDao;
import com.bootcamp.miniproject.dao.OutletDaoImpl;
import com.bootcamp.miniproject.model.Item;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.ItemVariant;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.User;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	ItemDao itemDao;
	
	@Autowired
	ItemVariantDao variantDao;
	
	@Autowired
	ItemInventoryDao inventoryDao;
	
	@Autowired
	OutletDao outletDao;
	
	@Autowired
	HttpSession httpSession;
	
	public List<Item> getAll() {
		return itemDao.getAll();
	}

	public void save(Item item) {
		List<Outlet> outlet = outletDao.selectAll();
		Date date = new Date();
		List<ItemVariant> itemVariant = item.getItemVariant();
		User user = (User) httpSession.getAttribute("userLogin");
		System.out.println(user.getUsername());
		item.setCreatedBy(user);
		item.setModifiedBy(user);
		item.setCreatedOn(date);
		item.setItemVariant(null);
		itemDao.save(item);
		
		ItemInventory inventory;
		for(ItemVariant ivar : itemVariant) {
			inventory = ivar.getItemInventory().get(0);
			ivar.setItemInventory(null);
			ivar.setCreatedBy(user);
			ivar.setModifiedBy(user);
			ivar.setCreatedOn(date);
			ivar.setItem(item);
			variantDao.save(ivar);
					
			for (Outlet out :outlet) {
				System.out.println(out.getName());
				ItemInventory inv = new ItemInventory();
				inv.setItemVariant(ivar);
				inv.setBeginning(inventory.getBeginning());
				inv.setEndingQty(inventory.getBeginning());
				inv.setAlertAtQty(inventory.getAlertAtQty());
				inv.setOutlet(out);
				inv.setCreatedBy(user);
				inv.setModifiedBy(user);
				inv.setCreatedOn(date);
				inventoryDao.save(inv);
			}			
		}
	}
	
	public void update(Item item) {
		List<Outlet> outlet = outletDao.selectAll();
		List<ItemVariant> itemVariant = item.getItemVariant();
		User user = (User) httpSession.getAttribute("userLogin");
		System.out.println(user.getEmployee().getFirstName());
		item.setItemVariant(null);
		item.setModifiedBy(user);
		itemDao.update(item);
		
		ItemInventory inventory;
		System.out.println(itemVariant.size());
		for(ItemVariant ivar: itemVariant) {
			inventory = ivar.getItemInventory().get(0);
			ivar.getItemInventory().clear();
			ivar.setModifiedBy(user);
			ivar.setItem(item);
			
			if(ivar.getId() == null) {
				variantDao.save(ivar);
				System.out.println(inventory.getId() == null);
				if (inventory.getId() == null) {
					System.out.println("null");
					for (Outlet out :outlet) {
						System.out.println(out.getName());
						ItemInventory inv = new ItemInventory();
						inv.setItemVariant(ivar);
						inv.setAlertAtQty(inventory.getAlertAtQty());
						inv.setBeginning(inventory.getBeginning());
						inv.setEndingQty(inventory.getBeginning());
						inv.setOutlet(out);
						inv.setCreatedBy(user);
						inv.setModifiedBy(user);
						inv.setCreatedOn(new Date());
						inventoryDao.save(inv);					
					}
				}
				
			} else {
				ivar.setModifiedBy(user);
				variantDao.update(ivar);
				System.out.println("beres update variant");
//				
				for (Outlet out :outlet) {
					System.out.println(out.getName());
					// Belum fix
//					ItemInventory inv = new ItemInventory();
//					inv.setAlertAtQty(inventory.getAlertAtQty());
//					inv.setItemVariant(ivar);
//				
//					inv.setBeginning(inventory.getBeginning());
//					inv.setOutlet(out);
//					if (inv.getId() == null) {
//						inv.setEndingQty(inventory.getBeginning());
//						inventoryDao.save(inv);
//					} else {
//						inventoryDao.update(inv);
//					}
					
				}
			}
		}
	}
	
	public Item getOne(long id) {
		return itemDao.getOne(id);
	}

	public void delete(Item item) {
		itemDao.delete(item);
	}

	public List<Item> getItemBySearchName(String search) {
		return itemDao.getItemBySearchName(search);
	}

}
