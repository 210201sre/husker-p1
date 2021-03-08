package com.revature.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Item;
import com.revature.models.Ledger;
import com.revature.models.User;
import com.revature.repositories.ItemDAO;
import com.revature.repositories.LedgerDAO;

@Service
public class ItemService {

	@Autowired
	private ItemDAO itemDAO;

	@Autowired
	private LedgerDAO ledgerDAO;

	private static final Logger log = LoggerFactory.getLogger(ItemService.class);

	public Item addItem(Item i) {
		MDC.put("event", "Add Item");

		log.info("Adding new item");
		LocalDateTime time = LocalDateTime.now();
		i.setTimeAdded(time);
		itemDAO.save(i);

		BigDecimal price = i.getSellPrice();
		BigDecimal quantity = BigDecimal.valueOf(i.getQuantity());
		BigDecimal total = price.multiply(quantity);
		BigDecimal transactionTotal = total.multiply(BigDecimal.valueOf(-1));

		User store = new User();
		store.setUserID(1);
		Ledger buyTransaction = new Ledger();
		buyTransaction.setUser(store);
		buyTransaction.setItem(i);
		buyTransaction.setTransactionQuantity(i.getQuantity());
		buyTransaction.setTransactionTime(time);
		buyTransaction.setTransactionTotal(transactionTotal);

		MDC.put("itemId", Integer.toString(i.getItemID()));
		log.info("Successfully added new item");
		log.info("Transaction amount = " + transactionTotal);

		ledgerDAO.save(buyTransaction);

		return itemDAO.save(i);
	}

	public List<Item> findAll() {
		MDC.put("event", "Find All Items");
		List<Item> itemList = itemDAO.findAll();
		for (int i = 0; i < itemList.size(); i++) {
			log.info("Found Item with itemID" + itemList.get(i).getItemID());
		}
		log.info("Found list of all items");
		return itemDAO.findAll();
	}

	public Optional<Item> findSingleItem(int id) {
		MDC.put("event", "Find Single Item");
		MDC.put("itemId", Integer.toString(id));
		log.info("Successfully found Item with ID = " + id);
		return itemDAO.findById(id);
	}

	public void deleteItem(Item i) {
		MDC.put("event", "Delete Item");
		MDC.put("itemId", Integer.toString(i.getItemID()));

		log.info("Delete successful!");

		itemDAO.delete(i);
	}

	public Item update(Item i) {
		MDC.put("event", "Update Item");
		MDC.put("itemId", Integer.toString(i.getItemID()));

		log.info("Update successful!");	
		return itemDAO.save(i);
	}
}
