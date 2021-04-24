package com.ara.photoalvand.controllers;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.services.categoryImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/file")
public class fileController {
	
	@Autowired categoryImp service;

	@PostMapping("/upload")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public String create(@RequestBody category category) {
		System.out.println("create category : "+category.getName());
		return service.create(category) +"";
	}
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity <Iterable<category>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(service.list());
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
