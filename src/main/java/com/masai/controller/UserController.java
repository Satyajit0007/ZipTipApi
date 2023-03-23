package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.Address;
import com.masai.model.User;
import com.masai.service.CustomerService;



@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public class UserController {

	@Autowired
	private CustomerService cService;
	
	@PostMapping("/users")
	public ResponseEntity<User> saveCustomer(@RequestBody User user) throws UserException{
		User saveCustomer = cService.createUser(user);
		return new ResponseEntity<User>(saveCustomer,HttpStatus.CREATED);
	}
	
	@PutMapping("/users/address")
	public ResponseEntity<User> addAddress(@RequestBody User user, @RequestParam(required= false) String key) throws UserException{
		User updateUser = cService.addAddress(user, key);
    	return new ResponseEntity<User>(updateUser,HttpStatus.OK);
	}
	
	@PutMapping("/users/update")
	public ResponseEntity<User> updateCustomer(@RequestBody User user, @RequestParam(required= false) String key) throws UserException{
		User updateUser = cService.updateUser(user, key);
    	return new ResponseEntity<User>(updateUser,HttpStatus.OK);
	}
	@GetMapping("/users/addresslist/{key}")
	public ResponseEntity<List<Address>> getAllAddress(@PathVariable("key") String key ) throws LoginException, UserException{
		List<Address> addressList = cService.getUserAddressById(key);
		return new ResponseEntity<List<Address>>(addressList,HttpStatus.OK);
	}
	
	@GetMapping("/users/{key}")
	public ResponseEntity<User> getUserByIdHandler(@PathVariable("key") String key ) throws  UserException{
		User user = cService.getUserById(key);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
