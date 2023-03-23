package com.masai.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.AdminDao;
import com.masai.dao.CartDao;
import com.masai.dao.PaymentDao;
import com.masai.dao.SessionDao;
import com.masai.exception.CartException;
import com.masai.exception.UserException;
import com.masai.model.Admin;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Payment;
import com.masai.model.User;
import com.masai.service.PaymentService;

@Service(value="payment")
public class PaymentServiceImpl  implements PaymentService{
	
	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private SessionDao sDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private AdminDao aDao;
		
	@Override
	public Payment paymentStatus(String key) throws UserException, CartException {
		
		CurrentUserSession validUserSession = sDao.findByUuid(key);
		if(validUserSession == null) {
		 throw new UserException("Please Log In First");	
		}
		
		
		List<Cart> cart =  cartDao.findByUserId(validUserSession.getCurrentUserId());
		
		if(cart.size() != 0) {
			Payment userPayment = new Payment();
			userPayment.setLocalDateTime(LocalDateTime.now());
			userPayment.setUserId(validUserSession.getCurrentUserId());
			String utr = "abc"+cart.get(0).getUserName()+"xyz";
			userPayment.setUTR(utr);
			if(utr.equals(null)) {
				userPayment.setPaymentStatus(false);
			}else {
				userPayment.setPaymentStatus(true);
			}
			Optional<Admin> admin = aDao.findById(cart.get(0).getResturentId());
			if(admin.isPresent()) {
			userPayment.setAdmin(admin.get());
			
			return paymentDao.save(userPayment);
			}else {
				throw new UserException("Problem in fetching Resturent");
			}
		}else {
			throw new CartException("Cart Is Empty Please Add Item");
		}
		
		
	}

}
