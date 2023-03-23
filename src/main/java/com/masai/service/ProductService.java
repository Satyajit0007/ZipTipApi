package com.masai.service;

import java.util.List;

import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Product;

public interface ProductService {

	
	public List<Product> getProductList(String key) throws ProductException, UserException;
    public List<Product> getProdutListByRestaurent(Integer id) throws ProductException, UserException;
    
    
}
