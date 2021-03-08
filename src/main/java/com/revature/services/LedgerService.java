package com.revature.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Ledger;
import com.revature.models.User;
import com.revature.repositories.ItemDAO;
import com.revature.repositories.LedgerDAO;

@Service
public class LedgerService {
	
	@Autowired
	private LedgerDAO ledgerDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	private static final Logger log = LoggerFactory.getLogger(ItemService.class);

	
	public Ledger makeTransaction(Ledger l) {
		
		MDC.put("event", "Add Transaction");

		log.info("Creating new Transaction");
		
		LocalDateTime time = LocalDateTime.now();
		l.setTransactionTime(time);
		ledgerDAO.save(l);
		int transactionQuantity = l.getTransactionQuantity();
		BigDecimal buyPrice = itemDAO.getBuyPrice(ledgerDAO.getItemID(l.getTransactionID()));
		BigDecimal quantity = BigDecimal.valueOf(transactionQuantity);
		l.setTransactionTotal(buyPrice.multiply(quantity));
		itemDAO.updateQuantity(transactionQuantity, ledgerDAO.getItemID(l.getTransactionID()));
		ledgerDAO.saveAndFlush(l);
		
		MDC.put("transactionId", Integer.toString(l.getTransactionID()));
		log.info("Successfully created transaction");
		return l;
	}
	
	public List<Ledger> findAll(){
		MDC.put("event", "Find All Transactions");
		List<Ledger> ledgerList = ledgerDAO.findAll();
		for (int i = 0; i < ledgerList.size(); i++) {
			log.info("Found Transaction with transactionID" + ledgerList.get(i).getTransactionID());
		}
		log.info("Found list of all transactions");
		return ledgerDAO.findAll();
	}
	
	public void deleteTransaction(Ledger l) {
		MDC.put("event", "Delete Transaction");
		MDC.put("userId", Integer.toString(l.getTransactionID()));
		log.info("Succesfully deleted Item with ID = " + Integer.toString(l.getTransactionID()));
		ledgerDAO.delete(l);
	}
	
	public BigDecimal getGrossProfit() {
		MDC.put("event", "Aggregate Operation");
		BigDecimal profit = ledgerDAO.getGrossProfit();
		log.info("Obtained Gross Profit: $" + profit.toString());
		return ledgerDAO.getGrossProfit();
	}
	
	public BigDecimal getLosses() {
		MDC.put("event", "Aggregate Operation");
		BigDecimal losses = ledgerDAO.getLosses();
		log.info("Obtained Losses: $" + losses.toString());
		return ledgerDAO.getLosses();
	}
	
	public BigDecimal getSales() {
		MDC.put("event", "Aggregate Operation");
		BigDecimal sales = ledgerDAO.getSales();
		log.info("Obtained sales: $" + sales.toString());
		return ledgerDAO.getSales();
	}	

}
