package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.LogInDTO;
import com.masai.service.LoginService;

@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public  class UserLoginController {

	@Autowired
	@Qualifier("user")
	private LoginService LoginService;
	
	@PostMapping("/userlogin")
	public ResponseEntity<CurrentUserSession> logInUser(@RequestBody LogInDTO dto) throws LoginException, UserException{
		CurrentUserSession result = LoginService.logIntoAccount(dto);
		return new ResponseEntity<CurrentUserSession>(result,HttpStatus.OK);
	}
	
	@PostMapping("/userlogout")
	public ResponseEntity<String> logoutUser(@RequestParam(required = false) String key ) throws LoginException, UserException{
	  String result = LoginService.LogoutFromAccount(key);
	  return new ResponseEntity<String>(result,HttpStatus.OK);
	}
}
