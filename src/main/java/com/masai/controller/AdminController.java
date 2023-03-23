package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.UserException;
import com.masai.model.Admin;
import com.masai.service.AdminService;

@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public class AdminController {

	@Autowired
	private AdminService aService;
		
	
	@PostMapping("/admin")
	public ResponseEntity<Admin>saveAdminHandler(@RequestBody Admin admin) throws UserException{
		Admin saveAdmin = aService.registerAdmin(admin);
		return new ResponseEntity<Admin>(saveAdmin,HttpStatus.CREATED);
	}
	
	@GetMapping("/restaurents")
	public ResponseEntity<List<Admin>>allRestaurentListHandler() throws UserException{
	  List<Admin> adminList = aService.getAllRestaurentList();
	  return new ResponseEntity<List<Admin>>(adminList,HttpStatus.CREATED);
	}
	
	@GetMapping("/restaurents/{adminId}")
	public ResponseEntity<Admin>RestaurentByIdHandler(@PathVariable("adminId") Integer adminId ) throws UserException{
	  Admin admin = aService.getRestaurentById(adminId);
	  return new ResponseEntity<Admin>(admin,HttpStatus.OK);
	}
}
