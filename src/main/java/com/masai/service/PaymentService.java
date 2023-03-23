   package com.masai.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.masai.exception.CartException;
import com.masai.exception.UserException;
import com.masai.model.Payment;

@Service
public interface PaymentService {
	
      public Payment paymentStatus(String key) throws UserException, CartException;
     
}
