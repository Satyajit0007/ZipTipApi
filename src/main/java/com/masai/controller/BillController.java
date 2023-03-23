package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.OrderException;
import com.masai.exception.UserException;
import com.masai.model.Bill;
import com.masai.service.BillService;
import com.masai.service.CustomerService;

@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public class BillController {

	@Autowired
	private BillService billService;
	
	@PostMapping("/bill")
	public ResponseEntity<Bill> generateBillOrder(@RequestParam String key) throws UserException, OrderException{
		Bill bill = billService.generateBill(key);
		return new ResponseEntity<Bill>(bill,HttpStatus.ACCEPTED);
	}
	
}
