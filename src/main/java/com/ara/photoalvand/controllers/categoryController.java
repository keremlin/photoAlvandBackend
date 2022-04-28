package com.ara.photoalvand.controllers;

import java.util.List;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.services.Category;
import com.ara.photoalvand.viewModels.VMcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
@Validated
public class categoryController {
	
	@Autowired 
	private Category service;

	@PostMapping("/create")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<category> create(@RequestBody category category) {
		return ResponseEntity.status(HttpStatus.OK).body(service.create(category));
	}
	
	@GetMapping("/list")
	public ResponseEntity <Iterable<category>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(service.list());
	}

	@GetMapping("/findByID/{item}")
	public ResponseEntity<category> findCategoryByID(@PathVariable int item) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(item));
	}

	@GetMapping("/withRandomPictureAndFindById/{item}")
	public ResponseEntity<VMcategory> withRandomPictureAndFindById(@PathVariable int item) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findWithRandomImage(item));
	}
	@GetMapping("/getRandomCategory/{number}")
	public ResponseEntity<Iterable<VMcategory>> getRandomCategory(@PathVariable int number){
		return ResponseEntity.status(HttpStatus.OK).body(service.getRandomCategory(number));
	}
	@GetMapping("/getAllCategories")
	public ResponseEntity<List<VMcategory>> getAllCategories(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllCategories());
	}
}
