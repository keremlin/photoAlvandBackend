package com.ara.photoalvand.controllers;

import java.util.List;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.services.categoryImp;

import com.ara.photoalvand.viewModels.VMcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class categoryController {
	
	@Autowired 
	private categoryImp service;

	@PostMapping("/create")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public String create(@RequestBody category category) {
		System.out.println("create category : "+category.getName());
		return service.create(category) +"";
	}
	
	@GetMapping("/list")
//	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity <Iterable<category>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(service.list());
	}

	@GetMapping("/findByID/{item}")
	//@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<category> findCategoryByID(@PathVariable int item) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(item));
	}

	@GetMapping("/withRandomPictureAndFindById/{item}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<VMcategory> withRandomPictureAndFindById(@PathVariable int item) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findWithRandomImage(item));
	}
	@GetMapping("/getRandomCategory/{number}")
	public ResponseEntity<Iterable<VMcategory>> getRandomeCategory(@PathVariable int number){
		return ResponseEntity.status(HttpStatus.OK).body(service.getRandomeCateory(number));
	}
	@GetMapping("/getAllCategories")
<<<<<<< HEAD
	public ResponseEntity<List<category>> getAllCategories(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllCategories());
	}
=======
	public ResponseEntity<Iterable<VMcategory>> getAllCategories(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllCategories());
	}

>>>>>>> 833e0bae060ea661a67ae82e116d1132d8cca02a
}
