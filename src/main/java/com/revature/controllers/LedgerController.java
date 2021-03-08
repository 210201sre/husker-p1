package com.revature.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Ledger;
import com.revature.services.LedgerService;

@RestController
@RequestMapping("/ledger")
@Transactional
public class LedgerController {
	
	@Autowired
	private LedgerService ledgerService;
	
	private EntityManager em;
		
	public LedgerController(LedgerService ledgerService, EntityManager em) {
		this.ledgerService = ledgerService;
		this.em = em;
	}
	
	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Ledger> makeTransaction(@RequestBody Ledger l){
		Ledger result = ledgerService.makeTransaction(l);
		em.refresh(result);
		MDC.clear();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping
	public ResponseEntity<List<Ledger>> findAll(){
		MDC.clear();
		return ResponseEntity.ok(ledgerService.findAll());
	}
	
	@GetMapping("/profit")
	public ResponseEntity<BigDecimal> getGrossProfit(){
		MDC.clear();
		return ResponseEntity.ok(ledgerService.getGrossProfit());
	}
	
	@GetMapping("/losses")
	public ResponseEntity<BigDecimal> getLosses(){
		MDC.clear();
		return ResponseEntity.ok(ledgerService.getLosses());
	}
	
	@GetMapping("/sales")
	public ResponseEntity<BigDecimal> getSales(){
		MDC.clear();
		return ResponseEntity.ok(ledgerService.getSales());
	}
	
	@DeleteMapping
	public void deleteItem(@RequestBody Ledger l) {
		MDC.clear();
		ledgerService.deleteTransaction(l);
	}
}
