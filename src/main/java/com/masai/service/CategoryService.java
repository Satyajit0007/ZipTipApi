package com.masai.service;

import java.util.List;

import com.masai.exception.CategoryException;
import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.Category;


public interface CategoryService {

public Category addCategory(String key, String categoryName) throws CategoryException, LoginException, UserException;
	
	public Category removeCategory(String key, String categoryName) throws CategoryException, LoginException;

	public Category viewCategoryById(String key, Integer categoryId) throws CategoryException, LoginException;
	
	public Category updateCategory(String key, Category category) throws CategoryException, LoginException;

	public List<Category> viewAllCategory(String key) throws CategoryException, LoginException;
}
