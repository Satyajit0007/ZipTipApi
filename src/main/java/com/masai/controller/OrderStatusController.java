package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.OrderException;
import com.masai.exception.UserException;
import com.masai.model.Orders;
import com.masai.service.AdminService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public class OrderStatusController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/orderstatus/{id}")
	public ResponseEntity<Orders> OrderStatusController(@RequestBody Orders order, @RequestParam String key, Integer id) throws UserException, OrderException{
		Orders orders = adminService.updateOrderStatus(order, key, id);
	    return new 	ResponseEntity<Orders>(orders,HttpStatus.ACCEPTED);		
	}  
}
