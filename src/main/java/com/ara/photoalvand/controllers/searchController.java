package com.ara.photoalvand.controllers;

import java.util.List;

import com.ara.photoalvand.services.search;
import com.ara.photoalvand.viewModels.VMsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/search")
public class searchController {
	@Autowired
	private search searchService;
	@GetMapping("/byCategory/{category}")
	public ResponseEntity<List<VMsearch>> byCategory(@PathVariable int category) {
		return ResponseEntity.status(HttpStatus.OK).body(searchService.byCategory(category));
	}
	
}
