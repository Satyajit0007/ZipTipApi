package com.masai.service;

import com.masai.exception.OrderException;
import com.masai.exception.UserException;
import com.masai.model.Bill;
import com.masai.model.User;

public interface BillService {

	public Bill generateBill(String key) throws UserException, OrderException;

//	User addAddress(User user, String key) throws UserException;
}
