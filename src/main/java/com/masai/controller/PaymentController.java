package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.CartException;
import com.masai.exception.UserException;
import com.masai.model.Payment;
import com.masai.service.PaymentService;

@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public class PaymentController  {

	@Autowired
    @Qualifier("payment")
	private PaymentService pService;
	
	@PostMapping("/payment")
	public ResponseEntity<Payment> paymentHandler(@RequestParam String key) throws UserException, CartException{
		Payment payment = pService.paymentStatus(key);
		return new ResponseEntity<Payment>(payment,HttpStatus.OK);
	} 
}
