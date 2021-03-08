package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Item;
import com.revature.services.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public ResponseEntity<List<Item>> findAll() {
		MDC.clear();
		return ResponseEntity.ok(itemService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Item>> findSingleItem(@PathVariable("id") int itemID) {
		MDC.clear();
		return ResponseEntity.ok(itemService.findSingleItem(itemID));
	}

	@PostMapping
	public ResponseEntity<Item> addItem(@RequestBody Item i) {
		MDC.clear();
		return ResponseEntity.ok(itemService.addItem(i));
	}

	@PutMapping
	public ResponseEntity<Item> updateItem(@RequestBody Item i) {
		MDC.clear();
		return ResponseEntity.ok(itemService.update(i));
	}

	@DeleteMapping
	public void deleteItem(@RequestBody Item i) {
		MDC.clear();
		itemService.deleteItem(i);
	}
}
