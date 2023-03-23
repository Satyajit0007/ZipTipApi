package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.CategoryException;
import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.Category;
import com.masai.service.CategoryService;

@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public class CategoryController {

	@Autowired
	private CategoryService cService;
	
	@PostMapping("/category")
	public ResponseEntity<Category> addCategory(@RequestParam(required = false) String key,
			@RequestParam String categoryName) throws CategoryException, LoginException, UserException  {
		Category newCategory = cService.addCategory(key, categoryName);
		return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
	}
}
