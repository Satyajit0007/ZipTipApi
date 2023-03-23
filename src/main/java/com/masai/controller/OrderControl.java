package com.masai.controller;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.OrderDetails;
import com.masai.model.Orders;
import com.masai.service.CustomerService;

@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public class OrderControl {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/getorders")
	public ResponseEntity<OrderDetails> getOrderDetailsHandler(@RequestParam String key ,@RequestParam (required = true) Integer id ) throws OrderException, LoginException, UserException{
		OrderDetails order = customerService.getOrderDetails(key,id);
		return new ResponseEntity<OrderDetails>(order,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getBill/{key}")
	public ResponseEntity<OrderDetails> getBillHandler(@PathVariable("key") String key ) throws LoginException, ProductException{
		OrderDetails order = customerService.getBillByuser(key);
		return new ResponseEntity<OrderDetails>(order,HttpStatus.ACCEPTED);
	}
	
	
}
