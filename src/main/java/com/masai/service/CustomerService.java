package com.masai.service;


import java.util.List;
import com.masai.exception.CartException;
import com.masai.exception.CategoryException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Address;
import com.masai.model.Bill;
import com.masai.model.Cart;
import com.masai.model.OrderDetails;
import com.masai.model.Orders;
import com.masai.model.User;



public interface CustomerService {
	
	public User createUser(User user)throws UserException;
	public User addAddress(User user , String key)throws UserException;
	public User updateUser(User user , String key)throws UserException;
	public Cart addToCart(String key, Integer id, Integer qty) throws  CartException, LoginException, UserException, CategoryException, ProductException;
	public OrderDetails getOrderDetails(String key ,Integer addressId) throws OrderException, LoginException, UserException;
	public List<Address> getUserAddressById(String uuid) throws LoginException, UserException;
    public OrderDetails getBillByuser(String uniqueKey) throws LoginException, ProductException;
    public User getUserById(String uniqueKey) throws UserException;
    
}