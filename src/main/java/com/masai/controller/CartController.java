package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.CartException;
import com.masai.exception.CategoryException;
import com.masai.exception.LoginException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Cart;
import com.masai.service.CustomerService;

@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public class CartController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addtocart")
	public ResponseEntity<Cart> addToCartHandler(@RequestParam String key, @RequestParam Integer id , @RequestParam Integer qty) throws CartException, LoginException, UserException, CategoryException, ProductException{
		Cart cart = customerService.addToCart(key, id,qty);
		return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
	}
}
    
