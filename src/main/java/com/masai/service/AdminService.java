package com.masai.service;

import java.util.List;

import com.masai.exception.OrderException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Admin;
import com.masai.model.Bill;
import com.masai.model.Orders;
import com.masai.model.Product;


public interface AdminService {
 
	public Admin registerAdmin(Admin admin)throws UserException;
	public Product addProduct(Product product, String key) throws ProductException, UserException;
    public List<Product> getProductListByAdminId(String key) throws ProductException, UserException;
    public Orders updateOrderStatus(Orders order ,String key, Integer OrderId) throws UserException, OrderException;
    public List<Admin> getAllRestaurentList() throws UserException;
    public Admin getRestaurentById(Integer adminId) throws UserException;
    
}
