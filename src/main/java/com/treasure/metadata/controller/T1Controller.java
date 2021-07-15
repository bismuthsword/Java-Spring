package com.treasure.metadata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.treasure.metadata.model.pg.T1;
import com.treasure.metadata.repository.pg.T1Repository;

@RestController
public class T1Controller {

	@Autowired
	private T1Repository repository;

	@GetMapping("/t1")
	public List<T1> getAllT1() {
		return repository.findAll();
	}

	@GetMapping("/t1/{id}")
	public List<T1> getT1ByC1(@PathVariable int id) {
		return repository.findByC1(id);
	}
}
