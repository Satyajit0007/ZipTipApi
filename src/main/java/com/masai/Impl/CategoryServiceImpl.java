package com.masai.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.AdminDao;
import com.masai.dao.CategoryDao;
import com.masai.dao.SessionDao;
import com.masai.exception.CategoryException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.exception.UserException;
import com.masai.model.Admin;
import com.masai.model.Bill;
import com.masai.model.Category;
import com.masai.model.CurrentUserSession;
import com.masai.model.Orders;
import com.masai.model.User;
import com.masai.service.CategoryService;

@Service(value = "Category")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private SessionDao sDao;

	@Autowired
	private AdminDao aDao; 
	
	@Autowired
	private CategoryDao cDao;
	
	@Override
	public Category addCategory(String key, String categoryName) throws CategoryException, LoginException, UserException {
	
		CurrentUserSession validSession = sDao.findByUuid(key);
		if(validSession == null) {
			throw new UserException("Login First..!");
		}
		
		Optional<Admin> admin = aDao.findById(validSession.getCurrentUserId());
		
		if(admin.isPresent()) {
			
             Category exixtsCatogory = cDao.findByCategoryName(categoryName);
			
			if (exixtsCatogory != null) {
				throw new CategoryException("Category already exists!");
			}
			Category category = new Category();
			category.setCategoryName(categoryName);
			Category newCategory = cDao.save(category);
			return newCategory;
			
		}else {
			throw new UserException("Access Denied");
		}
		
		
	
	}

	@Override
	public Category removeCategory(String key, String categoryName) throws CategoryException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category viewCategoryById(String key, Integer categoryId) throws CategoryException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category updateCategory(String key, Category category) throws CategoryException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> viewAllCategory(String key) throws CategoryException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
