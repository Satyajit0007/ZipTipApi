package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {

	public Category findByCategoryName(String categoryName);
	

}
