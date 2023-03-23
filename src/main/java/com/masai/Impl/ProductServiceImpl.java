package com.masai.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.ProductDao;
import com.masai.dao.SessionDao;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Product;
import com.masai.service.ProductService;

@Service(value = "product")
public class ProductServiceImpl implements ProductService {
          
	@Autowired
	private SessionDao sDao;
	      
	@Autowired
	private ProductDao pDao;
	      
	      
	@Override
	public List<Product> getProductList(String key) throws ProductException, UserException {
		  
    	 CurrentUserSession validSession = sDao.findByUuid(key);
    	  
	     List<Product> productList = pDao.findAll();
	     return productList;
	}


	@Override
	public List<Product> getProdutListByRestaurent(Integer id) throws ProductException, UserException {

		List<Product> productList = pDao.findByAdminId(id);
	    return productList;
		
	}    

	

}
